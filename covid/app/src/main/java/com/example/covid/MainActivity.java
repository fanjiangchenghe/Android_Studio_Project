package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private static final String TAG ="MainActivity";
    CountryCodePicker countryCodePicker;
    TextView mtodaytotal,mtotal,mactive,mtodayactive,mrecovered,mtodayrecovered,mdeaths,mtodaydeath;
    String country="China";
    TextView mfilter;
    Spinner spinner;
    String[] types={"累计确诊","累计死亡","累计治愈","现有确诊"};
    private List<ModelClass> modelClassList;
    private List<ModelClass> modelClassList2;
    PieChart mpiechart;
    private RecyclerView recyclerView;
    com.example.covid.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        System.out.println("------------------1111111111111111-
//        -----------");
        Log.d(TAG,"onCreate(Bundle?) called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //绑定组件
        countryCodePicker=findViewById(R.id.ccp);
        mtodayactive=findViewById(R.id.todayactive);
        mactive=findViewById(R.id.activecase);
        mdeaths=findViewById(R.id.totaldeath);
        mtodaydeath=findViewById(R.id.todaydeath);
        mrecovered=findViewById(R.id.recoveredcase);
        mtodayrecovered=findViewById(R.id.todayrecovered);
        mtotal=findViewById(R.id.totalcase);
        mtodaytotal=findViewById(R.id.todaytotal);
        mpiechart=findViewById(R.id.piechart);
        spinner=findViewById(R.id.spinner);
        mfilter=findViewById(R.id.filter);
        recyclerView=findViewById(R.id.recyclerview);
        modelClassList=new ArrayList<>();
        modelClassList2=new ArrayList<>();

        //spinner 配置适配器
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,types);
        //设定下拉框样式，不使用则默认和创建时相同
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(0,true);

        //发起网路请求，获取国家信息 赋值给modelClassList2  教程https://cloud.tencent.com/developer/article/1341880
        ApiUtilities.getAPIInterface().getcountrydata().enqueue(new Callback<List<ModelClass>>() {
            @Override
            public void onResponse(Call<List<ModelClass>> call, Response<List<ModelClass>> response) {
                modelClassList2.addAll(response.body());
                ModelClass res =  response.body().get(0);

//                adapter
                adapter.notifyDataSetChanged();
                Log.d(TAG,"response");
            }
            @Override
            public void onFailure(Call<List<ModelClass>> call, Throwable t) {
                Log.d(TAG,"failure");
            }
        });

        //自定义 adapter类
        adapter=new Adapter(getApplicationContext(),modelClassList2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        //设置国家  监听用户更改国家 更新数据
        countryCodePicker.setAutoDetectedCountry(true);
        country=countryCodePicker.getSelectedCountryName();
        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                country=countryCodePicker.getSelectedCountryName();
                fetchdata();
            }
        });

        fetchdata();


    }

    //更改数据
    private void fetchdata(){
        ApiUtilities.getAPIInterface().getcountrydata().enqueue(new Callback<List<ModelClass>>() {
            @Override
            public void onResponse(Call<List<ModelClass>> call, Response<List<ModelClass>> response) {
                modelClassList.addAll(response.body());
                for(int i=0;i<modelClassList.size();i++){
                    if(modelClassList.get(i).getCountry().equals(country)){

                        mactive.setText((modelClassList.get(i).getActive()));
                        mtodayactive.setText((modelClassList.get(i).getTodayCases()));
                        mtodaydeath.setText((modelClassList.get(i).getTodayDeaths()));
                        mtodayrecovered.setText((modelClassList.get(i).getTodayRecovered()));
                        mtodaytotal.setText((modelClassList.get(i).getTodayCases()));
                        mtotal.setText((modelClassList.get(i).getCases()));
                        mdeaths.setText((modelClassList.get(i).getDeaths()));
                        mrecovered.setText((modelClassList.get(i).getRecovered()));

                        int active,total,recovered,deaths;

                        active=Integer.parseInt(modelClassList.get(i).getActive());
                        total=Integer.parseInt(modelClassList.get(i).getCases());
                        recovered=Integer.parseInt(modelClassList.get(i).getRecovered());
                        deaths=Integer.parseInt(modelClassList.get(i).getDeaths());


                        //更新图片数据
                        updateGraph(active,total,recovered,deaths);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<ModelClass>> call, Throwable t) {

            }
        });
    }

    private void updateGraph(int active, int total, int recovered, int deaths) {

        mpiechart.clearChart();
        mpiechart.addPieSlice(new PieModel("累计确诊",total, Color.parseColor("#FFB701")));
        mpiechart.addPieSlice(new PieModel("现有确诊",active, Color.parseColor("#FF4CAF50")));
        mpiechart.addPieSlice(new PieModel("累计治愈",recovered, Color.parseColor("#38ACCD")));
        mpiechart.addPieSlice(new PieModel("累计死亡",deaths, Color.parseColor("#F55c47")));
        mpiechart.startAnimation();


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item=types[position];
        mfilter.setText(item);
        adapter.filter(item);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}