package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hbb20.CountryCodePicker;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citylist.CityListSelectActivity;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citylist.utils.CityListLoader;
import com.lljjcoder.style.citypickerview.CityPickerView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private static final String TAG ="MainActivity";
//    CountryCodePicker countryCodePicker;
    TextView mtodaytotal,mtotal,mactive,mtodayactive,mrecovered,mtodayrecovered,mdeaths,mtodaydeath;
    String country="China";
    TextView mfilter;
    Spinner spinner;
    String[] types={"累计确证","累计死亡","累计治愈","现有确证"};
//    private List<ModelClass> modelClassList;
//    private List<ModelClass> modelClassList2;
    PieChart mpiechart;
    private RecyclerView recyclerView;
    com.example.covid.Adapter adapter;




    TextView mcityname;
    CityPickerView mPicker=new CityPickerView();

    ChinaTotalClass chinaTotalClass;
    ChinaTotalClass chinaTotalClass2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        System.out.println("------------------1111111111111111-
//        -----------");
        Log.d(TAG, "onCreate(Bundle?) called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //绑定组件
//        countryCodePicker = findViewById(R.id.ccp);
        mtodayactive = findViewById(R.id.todayactive);
        mactive = findViewById(R.id.activecase);
        mdeaths = findViewById(R.id.totaldeath);
        mtodaydeath = findViewById(R.id.todaydeath);
        mrecovered = findViewById(R.id.recoveredcase);
        mtodayrecovered = findViewById(R.id.todayrecovered);
        mtotal = findViewById(R.id.totalcase);
        mtodaytotal = findViewById(R.id.todaytotal);
        mpiechart = findViewById(R.id.piechart);
        spinner = findViewById(R.id.spinner);
        mfilter = findViewById(R.id.filter);
        recyclerView = findViewById(R.id.recyclerview);
        mcityname = findViewById(R.id.cityname);


//        modelClassList = new ArrayList<>();
//        modelClassList2 = new ArrayList<>();


        //spinner 配置适配器
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, types);
        //设定下拉框样式，不使用则默认和创建时相同
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(0, true);

        //发起网路请求，获取国家信息 赋值给modelClassList2  教程https://cloud.tencent.com/developer/article/1341880
        ApiUtilities.getAPIInterface().getcountrydata().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                modelClassList2.addAll(response.body());

//                System.out.println("888888");
                Log.d(TAG, "response");
                if(response.isSuccessful()) {
                    Log.d("response", "response.body() == null");
                    System.out.println(response.body());
                }

//                Log.d(TAG,response.body().toString());
                try {
                    chinaTotalClass = new Gson().fromJson(response.body().string(),ChinaTotalClass.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                adapter
//                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "failure");
            }
        });


//        Log.d(TAG, chinaTotalClass.getTimestamp());
        //自定义 adapter类
//        adapter = new Adapter(getApplicationContext(), chinaTotalClass.getData().getAreaTree());
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(adapter);


        //设置城市

//        CityListLoader.getInstance().loadProData(this);


        CityConfig cityConfig = new CityConfig.Builder()
                .setCityWheelType(CityConfig.WheelType.PRO_CITY)
                .province("浙江省")//默认显示的省份
                .city("杭州市")//默认显示省份下面的城市
                .build();
        mPicker.init(this);
        mPicker.setConfig(cityConfig);

        mcityname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        Toast.makeText(MainActivity.this, province + " - " + city, Toast.LENGTH_LONG).show();
                        //省份province
                        //城市city
                        //地区district
                        mcityname.setText(city.toString());
                        fetchdata();
                    }

                    @Override
                    public void onCancel() {

                    }
                });

                //显示
                mPicker.showCityPicker();
            }
        });


    }
        //设置国家  监听用户更改国家 更新数据
//        countryCodePicker.setAutoDetectedCountry(true);
//        country=countryCodePicker.getSelectedCountryName();
//        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
//            @Override
//            public void onCountrySelected() {
//                country=countryCodePicker.getSelectedCountryName();
//                fetchdata();
//            }
//        });
//
//        fetchdata();
//
//
//    }

    //更改数据
    private void fetchdata(){
        ApiUtilities.getAPIInterface().getcountrydata().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    chinaTotalClass2 = new Gson().fromJson(response.body().string(),ChinaTotalClass.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Area province;
                for(int i=0;i<chinaTotalClass2.getData().getAreaTree().size();i++){
                    province=chinaTotalClass2.getData().getAreaTree().get(i);
                    for(int j=0;j<province.getChildrenList().size();j++) {
                        if (province.getChildrenList().get(i).getName().equals(mcityname)) {

                            mactive.setText((province.getChildrenList().get(i).getTotal().getSuspect()));
                            mtodayactive.setText((province.getChildrenList().get(i).getToday().getSuspect()));
                            mtodaydeath.setText((province.getChildrenList().get(i).getToday().getDead()));
                            mtodayrecovered.setText((province.getChildrenList().get(i).getToday().getHeal()));
                            mtodaytotal.setText((province.getChildrenList().get(i).getToday().getConfirm()));
                            mtotal.setText((province.getChildrenList().get(i).getTotal().getConfirm()));
                            mdeaths.setText((province.getChildrenList().get(i).getTotal().getDead()));
                            mrecovered.setText((province.getChildrenList().get(i).getTotal().getHeal()));

                            int active, total, recovered, deaths;

                            active = Integer.parseInt(province.getChildrenList().get(i).getTotal().getSuspect());
                            total = Integer.parseInt(province.getChildrenList().get(i).getTotal().getConfirm());
                            recovered = Integer.parseInt(province.getChildrenList().get(i).getTotal().getHeal());
                            deaths = Integer.parseInt(province.getChildrenList().get(i).getTotal().getDead());


                            //更新图片数据
                            updateGraph(active, total, recovered, deaths);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

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
//        adapter.filter(item);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}