package pl.edu.tirex.lvlup.panel.activity;

import android.os.Bundle;
import pl.edu.tirex.lvlup.panel.R;

public class HomeActivity extends SidebarActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.content_services);
    }
}
