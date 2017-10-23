package pl.edu.tirex.lvlup.panel.activity;

import android.content.Intent;
import android.os.Bundle;
import pl.edu.tirex.lvlup.panel.R;

public class SecuredActivity extends SidebarActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean checkSession()
    {
        if (super.checkSession())
        {
            return true;
        }
        System.out.println("BREAK SESSION :O " + this.getClass());
        this.getLoginPreferences().edit().clear().apply();
        this.finishAffinity();
        return false;
    }
}
