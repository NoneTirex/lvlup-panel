package pl.edu.tirex.lvlup.panel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import pl.edu.tirex.lvlup.api.models.service.ServiceInfo;
import pl.edu.tirex.lvlup.panel.R;
import pl.edu.tirex.lvlup.panel.ServicesArrayAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServicesActivity extends SecuredActivity implements AdapterView.OnItemClickListener
{
    private ListView serviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.content_services);

        this.serviceList = (ListView) this.findViewById(R.id.service_list);
        this.serviceList.setOnItemClickListener(this);

        this.execute(() -> this.getToken().getAllPaging(this.getToken()::getServiceList), this::serviceInfoResponse, (e) -> this.serviceInfoResponse(new ArrayList<>()));
    }

    public void serviceInfoResponse(List<ServiceInfo> services)
    {
        services.add(new ServiceInfo(666, false, 100, "Mój testowy serwer", System.currentTimeMillis() / 1000, System.currentTimeMillis() / 1000));
        services.add(new ServiceInfo(999, true, 100, "Domena", System.currentTimeMillis() / 1000, System.currentTimeMillis() / 1000));
        this.serviceList = (ListView) this.findViewById(R.id.service_list);
        this.serviceList.setAdapter(new ServicesArrayAdapter(this, services));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
    {
        Adapter adapter = adapterView.getAdapter();
        Object item = adapter.getItem(position);
        if (!(item instanceof ServiceInfo))
        {
            return;
        }
        ServiceInfo serviceInfo = (ServiceInfo) item;
        Intent intent = new Intent(this, ServiceActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra("id", serviceInfo.getId());
        this.startActivity(intent);
    }
}