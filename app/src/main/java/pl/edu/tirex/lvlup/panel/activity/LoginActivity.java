package pl.edu.tirex.lvlup.panel.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import pl.edu.tirex.lvlup.api.LvlupApi;
import pl.edu.tirex.lvlup.api.models.auth.LvlupToken;
import pl.edu.tirex.lvlup.panel.R;

public class LoginActivity extends SidebarActivity implements TextView.OnEditorActionListener, TextWatcher
{
    private AutoCompleteTextView emailText;
    private EditText passwordText;

    private View progressBar;

    private Button signInButton;

    private boolean alreadyLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.content_login);

        this.progressBar = this.findViewById(R.id.login_progress);

        this.emailText = (AutoCompleteTextView) this.findViewById(R.id.email);
        this.emailText.setOnEditorActionListener(this);
        this.emailText.addTextChangedListener(this);

        this.passwordText = (EditText) this.findViewById(R.id.password);
        this.passwordText.setOnEditorActionListener(this);
        this.passwordText.addTextChangedListener(this);

        this.signInButton = (Button) this.findViewById(R.id.sign_in_button);
        this.signInButton.setOnClickListener(view -> LoginActivity.this.tryLogin());
    }

    private void tryLogin()
    {
        if (this.alreadyLogin || !this.signInButton.isEnabled())
        {
            return;
        }
        this.signInButton.requestFocus();

        this.emailText.setError(null);
        this.passwordText.setError(null);

        final String email = this.emailText.getText().toString();
        final String password = this.passwordText.getText().toString();

        View focusView = null;

        if (email.isEmpty())
        {
            this.emailText.setError(this.getString(R.string.error_field_required));
            focusView = this.emailText;
        }
        if (password.isEmpty())
        {
            this.passwordText.setError(this.getString(R.string.error_field_required));
            focusView = this.passwordText;
        }
        if (focusView != null)
        {
            focusView.requestFocus();
            return;
        }
        this.switchProgress(true);
        this.alreadyLogin = true;
        this.execute(() -> LvlupApi.login(email, password), this::loginSuccess, this::loginError);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void switchProgress(final boolean show)
    {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        if (show)
        {
            this.signInButton.setText(null);
            this.signInButton.setEnabled(false);
        }
        else
        {
            this.signInButton.setText(R.string.action_sign_in);
            this.signInButton.setEnabled(true);
        }

        this.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        this.progressBar.animate().setDuration(shortAnimTime).alpha(show ? 1 : 0).setListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                LoginActivity.this.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    public void loginSuccess(LvlupToken token)
    {
        this.alreadyLogin = false;
        this.switchProgress(false);

        this.getLoginPreferences().edit().putString("authToken", token.getToken()).apply();

//        Intent intent = this.getParentActivityIntent();
//        if (intent == null)
//        {
//            intent = new Intent(this, HomeActivity.class);
//        }
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        this.startActivity(intent);
        this.finishAffinity();
    }

    public void loginError(Exception e)
    {
        this.alreadyLogin = false;
        this.switchProgress(false);

        if (e == null)
        {
            return;
        }

        Snackbar snackbar = Snackbar.make(this.getLayout(), e.getMessage(), Snackbar.LENGTH_SHORT);
        snackbar.show();

        System.out.println(e.getMessage());
    }

    @Override
    public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent)
    {
        if (id == EditorInfo.IME_ACTION_UNSPECIFIED)
        {
            this.tryLogin();
            return true;
        }
        return false;
    }

    @Override
    public void beforeTextChanged(CharSequence text, int i, int i1, int i2)
    {

    }

    @Override
    public void onTextChanged(CharSequence text, int start, int before, int count)
    {
        if (this.progressBar.getVisibility() == View.VISIBLE)
        {
            return;
        }
        this.signInButton.setEnabled(!this.passwordText.getText().toString().trim().isEmpty() && !this.emailText.getText().toString().trim().isEmpty());
    }

    @Override
    public void afterTextChanged(Editable editable)
    {

    }
}
