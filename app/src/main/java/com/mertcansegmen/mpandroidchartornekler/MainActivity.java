package com.mertcansegmen.mpandroidchartornekler;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LineChart lineChart = findViewById(R.id.line_chart);

        // Y ekseninde gözükecek değerler
        float[] kazanclar = {424.25f, 427.45f, 340.15f, 614.95f, 553.05f, 827.85f, 871.81f,
                             324.25f, 527.45f, 540.15f, 314.95f, 653.05f, 854.85f, 771.81f};

        // X ekseninin indexleri
        int[] indexler = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        // X ekseninde gözükecek değerler
        final String[] tarihler = new String[]{"6 May", "7 May", "8 May", "9 May", "10 May", "11 May", "12 May",
                                              "13 May", "14 May", "15 May", "16 May", "17 May", "18 May", "19 May"};

        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
            if(tarihler.length > (int) value)
                return tarihler[(int) value];
            else return "";
            }
        };

        List<Entry> degerler = new ArrayList<>();

        for(int i = 0; i<tarihler.length; i++){
            degerler.add(new BarEntry(indexler[i], kazanclar[i]));
        }

        LineDataSet dataSet = new LineDataSet(degerler, "Günlük Kâr (₺)");
        // Çizgi rengi
        dataSet.setColor(Color.GREEN);
        // Çizgi kalınlığı
        dataSet.setLineWidth(2f);
        // Noktaların kalınlığı
        dataSet.setCircleRadius(0.4f);
        // Noktaların rengi
        dataSet.setCircleColor(Color.GRAY);
        // Noktaların üzerinde değerlerin çıkmasını sağlar
        dataSet.setDrawValues(true);
        // Dokunmayla noktalara odaklanılmasını sağlar
        dataSet.setHighlightEnabled(false);

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);

        XAxis xEkseni = lineChart.getXAxis();
        // X eksenindeki değerlerin aşağıda gözükmesini sağlar
        xEkseni.setPosition(XAxis.XAxisPosition.BOTTOM);
        // X ekseni için ızgara çizgilerini gizler
        xEkseni.setDrawGridLines(false);
        // X ekseninde gözükecek değer sayısını ayarlar
        xEkseni.setLabelCount(7);
        // Yakınlaştırılınca x ekseninde fazladan değer çıkmasını engeller
        xEkseni.setGranularity(1f);
        // X ekseninin başlangıç boşluk bırakır
        xEkseni.setAxisMinimum(lineData.getXMin() - 0.5f);
        // X ekseninin bitiş boşluk bırakır
        xEkseni.setAxisMaximum(lineData.getXMax() + 0.5f);

        // Değer formatlayıcıyı ayarlar
        xEkseni.setValueFormatter(formatter);


        YAxis yEkseniSol = lineChart.getAxisLeft();
        // sol Y ekseninde gözükecek değer sayısını ayarlar
        yEkseniSol.setLabelCount(5, false);
        // sol Y ekseninin göstereceği minimum değeri ayarlar
        yEkseniSol.setAxisMinimum(0f);

        YAxis yEkseniSag = lineChart.getAxisRight();
        // sağ Y ekseninde gözükecek değer sayısını ayarlar
        yEkseniSag.setLabelCount(5, false);
        // sağ Y ekseninin göstereceği minimum değeri ayarlar
        yEkseniSag.setAxisMinimum(0f);
        // sağ Y ekseni için ızgara çizgilerini gizler çünkü sol Y ekseni zaten gösteriyor
        yEkseniSag.setDrawGridLines(false);
        // sağ Y ekseninin çizgisini gizler
        yEkseniSag.setDrawAxisLine(false);

        // X eksenindeki sadece 7 değerin gözükmesini sağlar
        lineChart.setVisibleXRangeMaximum(6);
        // Tablonun en sondaki değerlerin gözükmesini sağlar
        lineChart.moveViewToX(indexler.length*1f);

        // Grafiğe çift tıkla yakınlaştırmayı kapatır
        lineChart.setDoubleTapToZoomEnabled(false);
        // X eksenine animasyon uygulayarak grafiği 750 milisaniyede çizer
        lineChart.animateX(750);
        // PinchZoom aktif edilir. false ayarlanırsa X ve Y ekseni için ayrı ayrı yakınlaştırılır
        lineChart.setPinchZoom(true);
        // Grafiğin sağ altındaki açıklamayı siler
        lineChart.getDescription().setEnabled(false);
        // Grafiği yeniler
        lineChart.invalidate();
    }
}
