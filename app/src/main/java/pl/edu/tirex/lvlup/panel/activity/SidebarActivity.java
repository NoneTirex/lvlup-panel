package pl.edu.tirex.lvlup.panel.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import pl.edu.tirex.lvlup.api.Consumer;
import pl.edu.tirex.lvlup.api.Executor;
import pl.edu.tirex.lvlup.api.LvlupException;
import pl.edu.tirex.lvlup.api.models.auth.LvlupToken;
import pl.edu.tirex.lvlup.api.models.me.AccountInfo;
import pl.edu.tirex.lvlup.api.models.me.BalanceInfo;
import pl.edu.tirex.lvlup.api.utils.HashHelper;
import pl.edu.tirex.lvlup.panel.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.Executors;

public abstract class SidebarActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private static final SparseArray<Class<? extends Activity>> activityMap = new SparseArray<>();
    private static final java.util.concurrent.Executor asyncExecutor = Executors.newSingleThreadExecutor();
    private static final Handler handler = new Handler(Looper.getMainLooper());

    static
    {
        activityMap.put(R.id.sidebar_action_signin, LoginActivity.class);
        activityMap.put(R.id.sidebar_action_services, ServicesActivity.class);
    }

    private Toolbar toolbar;
    private DrawerLayout layout;
    private LinearLayout childLayout;
    private NavigationView navigation;

    private View headerView;

    private LvlupToken token;

    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        System.out.println("CREATE " + this.getClass() + " CREATE BY " + (this.getIntent() != null ? this.getIntent().getData() : null));
        this.layout = (DrawerLayout) this.getLayoutInflater().inflate(R.layout.activity_sidebar, null);

        this.toolbar = this.layout.findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);

        this.childLayout = this.layout.findViewById(R.id.sidebar_layout);

        this.setContentView(this.layout);

        this.navigation = (NavigationView) this.findViewById(R.id.sidebar_navigation);
        this.navigation.inflateMenu(R.menu.sidebar_notlogged);
        this.navigation.setNavigationItemSelectedListener(this);

        this.checkSession();
    }

    public boolean checkSession()
    {
        String authToken = this.getLoginPreferences().getString("authToken", null);
        if (authToken == null || authToken.isEmpty())
        {
            return false;
        }
        this.token = new LvlupToken(authToken);

        this.getNavigation().getMenu().clear();
        this.getNavigation().inflateMenu(R.menu.sidebar_logged);
        this.headerView = this.getNavigation().inflateHeaderView(R.layout.sidebar_header);

        this.executeSession(() -> this.token.getAccountInfo(), this::accountSuccess);
        this.executeSession(() -> this.token.getBalanceInfo(), this::balanceSuccess);
        return true;
    }

    public SharedPreferences getLoginPreferences()
    {
        return this.getSharedPreferences("lvlup-login", Context.MODE_PRIVATE);
    }

    public NavigationView getNavigation()
    {
        return navigation;
    }

    public <T> void executeSession(Executor<T, IOException> executor, Consumer<T> successConsumer)
    {
        this.execute(executor, successConsumer, this::sessionError);
    }

    public <T> void execute(Executor<T, IOException> executor, Consumer<T> successConsumer, Consumer<IOException> errorConsumer)
    {
        asyncExecutor.execute(() ->
        {
            try
            {
                T value = executor.execute();
                handler.post(() -> successConsumer.accept(value));
            }
            catch (LvlupException e)
            {
                if (errorConsumer != null)
                {
                    handler.post(() -> errorConsumer.accept(e));
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void setContentView(int layoutResID)
    {
        this.childLayout.removeAllViews();
        this.getLayoutInflater().inflate(layoutResID, this.childLayout, true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        this.layout.closeDrawers();
        if (item.getItemId() == R.id.sidebar_action_logout)
        {
            this.getLoginPreferences().edit().clear().apply();
            this.startActivity(this.getIntent());
            this.finish();
            return true;
        }
        Class<? extends Activity> activityClass = activityMap.get(item.getItemId());
        if (activityClass == null || activityClass.isInstance(this))
        {
            return false;
        }
        Intent intent = new Intent(this, activityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        this.startActivity(intent);
        return true;
    }

    public DrawerLayout getLayout()
    {
        return layout;
    }

    public Toolbar getToolbar()
    {
        return toolbar;
    }

    public LvlupToken getToken()
    {
        return token;
    }

    public void accountSuccess(AccountInfo accountInfo)
    {
        TextView email = this.headerView.findViewById(R.id.email);
        email.setText(accountInfo.getEmail());
        this.execute(() ->
        {
            URL url = new URL("https://www.gravatar.com/avatar/" + HashHelper.md5(accountInfo.getEmail().toLowerCase()) +"?d=mm");
            System.out.println(url.getPath());
            try (InputStream inputStream = url.openStream())
            {
                return BitmapFactory.decodeStream(inputStream);
            }
        }, this::downloadAvatar, null);
    }

    public void balanceSuccess(BalanceInfo balanceInfo)
    {
        TextView balance = this.headerView.findViewById(R.id.balance);
        balance.setText(balanceInfo.getBalancePretty());
    }

    public void downloadAvatar(Bitmap bitmap)
    {
        ImageView imageIcon = this.headerView.findViewById(R.id.icon);
        imageIcon.setImageBitmap(bitmap);
    }

    public void sessionError(Exception e)
    {
        e.printStackTrace();
        System.out.println("SESSION FAILED :O " + this.getClass());
        this.getLoginPreferences().edit().clear().apply();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        this.startActivity(intent);
        this.finishAffinity();
    }
}