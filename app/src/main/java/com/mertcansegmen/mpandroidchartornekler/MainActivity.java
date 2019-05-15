package com.mertcansegmen.mpandroidchartornekler;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

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
        int[] indexlerBar = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        // X ekseninde gözükecek değerler
        final String[] tarihler = new String[]{"6 May", "7 May", "8 May", "9 May", "10 May", "11 May", "12 May",
                                              "13 May", "14 May", "15 May", "16 May", "17 May", "18 May", "19 May"};

        ValueFormatter formatterLine = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
            if(tarihler.length > (int) value)
                return tarihler[(int) value];
            else return "";
            }
        };

        List<Entry> degerlerLine = new ArrayList<>();

        for(int i = 0; i<tarihler.length; i++){
            degerlerLine.add(new BarEntry(indexlerBar[i], kazanclar[i]));
        }

        LineDataSet lineDataSet = new LineDataSet(degerlerLine, "Günlük Kâr (₺)");
        // Çizgi rengi
        lineDataSet.setColor(Color.GREEN);
        // Çizgi kalınlığı
        lineDataSet.setLineWidth(2f);
        // Noktaların kalınlığı
        lineDataSet.setCircleRadius(0.4f);
        // Noktaların rengi
        lineDataSet.setCircleColor(Color.GRAY);
        // Noktaların üzerinde değerlerin çıkmasını sağlar
        lineDataSet.setDrawValues(true);
        // Dokunmayla noktalara odaklanılmasını sağlar
        lineDataSet.setHighlightEnabled(false);

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        XAxis xEkseniLine = lineChart.getXAxis();
        // X eksenindeki değerlerin aşağıda gözükmesini sağlar
        xEkseniLine.setPosition(XAxis.XAxisPosition.BOTTOM);
        // X ekseni için ızgara çizgilerini gizler
        xEkseniLine.setDrawGridLines(false);
        // Yakınlaştırılınca x ekseninde fazladan değer çıkmasını engeller
        xEkseniLine.setGranularity(1f);
        // X ekseninin başlangıcında boşluk bırakır
        xEkseniLine.setAxisMinimum(lineData.getXMin() - 0.5f);
        // X ekseninin sonunda boşluk bırakır
        xEkseniLine.setAxisMaximum(lineData.getXMax() + 0.5f);
        // Değer formatlayıcıyı ayarlar
        xEkseniLine.setValueFormatter(formatterLine);

        YAxis yEkseniSolLine = lineChart.getAxisLeft();
        // sol Y ekseninde gözükecek değer sayısını ayarlar
        yEkseniSolLine.setLabelCount(5, false);
        // sol Y ekseninin göstereceği minimum değeri ayarlar
        yEkseniSolLine.setAxisMinimum(0f);

        YAxis yEkseniSagLine = lineChart.getAxisRight();
        // sağ Y ekseninde gözükecek değer sayısını ayarlar
        yEkseniSagLine.setLabelCount(5, false);
        // sağ Y ekseninin göstereceği minimum değeri ayarlar
        yEkseniSagLine.setAxisMinimum(0f);
        // sağ Y ekseni için ızgara çizgilerini gizler çünkü sol Y ekseni zaten gösteriyor
        yEkseniSagLine.setDrawGridLines(false);
        // sağ Y ekseninin çizgisini gizler
        yEkseniSagLine.setDrawAxisLine(false);

        // X eksenindeki sadece 7 değerin gözükmesini sağlar
        lineChart.setVisibleXRangeMaximum(7);
        // Tablonun en sondaki değerlerin gözükmesini sağlar
        lineChart.moveViewToX(indexlerBar.length*1f);

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

        //**************************************************************************************//

        BarChart barChart = findViewById(R.id.bar_chart);

        // Y ekseninde gözükecek değerler
        float[] urunGetirileri = {871.81f, 853.81f, 827.85f, 758.85f, 614.95f, 598.95f, 553.05f,
                        534.05f, 427.45f, 426.45f, 401.25f, 378.25f, 290.15f, 240.15f};
        // X ekseninin indexleri
        float[] indexler = {0f, 1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f, 11f, 12f, 13f};
        // X ekseninde gözükecek değerler
        final String[] urunler= new String[]{"Browni Intense", "Kekstra Portakallı", "Albeni 40g", "Kekstra Çilekli",
                                            "Olala Barkek", "Indomie Noodle Dana", "Ülker Dido", "Ülker Çokonat",
                                            "Caramio", "Coco Star", "Eti Crax 90g", "Eti Hoşbeş Hnd Cvz 70g",
                                            "Laviva", "Eti Canga"};
        ValueFormatter formatterBar = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                if(urunler.length > (int) value)
                    return urunler[(int) value];
                else return "";
            }
        };

        List<BarEntry> degerlerBar = new ArrayList<>();

        for(int i = 0; i<tarihler.length; i++){
            degerlerBar.add(new BarEntry(indexler[i], urunGetirileri[i]));
        }

        BarDataSet barDataSet = new BarDataSet(degerlerBar, "Ürün Getirisi (₺)");
        // Bar rengi
        barDataSet.setColor(Color.CYAN);
        // Barların üzerinde değerlerin çıkmasını sağlar
        barDataSet.setDrawValues(true);
        // Dokunmayla noktalara odaklanılmasını engeller
        barDataSet.setHighlightEnabled(false);

        BarData barData = new BarData(barDataSet);
        // Bar kalınlığı
        barData.setBarWidth(0.5f);
        barChart.setData(barData);

        XAxis xEkseniBar = barChart.getXAxis();
        // X eksenindeki değerlerin aşağıda gözükmesini sağlar
        xEkseniBar.setPosition(XAxis.XAxisPosition.BOTTOM);
        // X ekseni için ızgara çizgilerini gizler
        xEkseniBar.setDrawGridLines(false);
        // Değer formatlayıcıyı ayarlar
        xEkseniBar.setValueFormatter(formatterBar);
        // Yakınlaştırılınca x ekseninde fazladan değer çıkmasını engeller
        xEkseniBar.setGranularity(1f);
        // X eksenindeki yazıları 45 derece sağa yatırır
        xEkseniBar.setLabelRotationAngle(-45);
        // X ekseninin başlangıcında boşluk bırakır
        xEkseniBar.setAxisMinimum(barData.getXMin() - 0.5f);
        // X ekseninin sonunda boşluk bırakır
        xEkseniBar.setAxisMaximum(barData.getXMax() + 0.5f);

        YAxis yEkseniSolBar = barChart.getAxisLeft();
        // sol Y ekseninde gözükecek değer sayısını ayarlar
        yEkseniSolBar.setLabelCount(5, false);
        // sol Y ekseninin göstereceği minimum değeri ayarlar
        yEkseniSolBar.setAxisMinimum(0f);

        YAxis yEkseniSagBar = barChart.getAxisRight();
        // sağ Y ekseninde gözükecek değer sayısını ayarlar
        yEkseniSagBar.setLabelCount(5, false);
        // sağ Y ekseninin göstereceği minimum değeri ayarlar
        yEkseniSagBar.setAxisMinimum(0f);
        // sağ Y ekseni için ızgara çizgilerini gizler çünkü sağ Y ekseni zaten gösteriyor
        yEkseniSagBar.setDrawGridLines(false);
        // sağ Y ekseninin çizgisini gizler
        yEkseniSagBar.setDrawAxisLine(false);

        // X eksenindeki sadece 7 değerin gözükmesini sağlar
        barChart.setVisibleXRangeMaximum(7);

        // Çift tıklayınca yakınlaştırmayı kapatır
        barChart.setDoubleTapToZoomEnabled(false);
        // X eksenine animasyon uygulayarak grafiği 750 milisaniyede çizer
        barChart.animateY(750);
        // PinchZoom aktif edilir. false ayarlanırsa X ve Y ekseni için ayrı ayrı yakınlaştırılır
        barChart.setPinchZoom(true);
        // Grafiğin sağ altındaki açıklamayı gizler
        barChart.getDescription().setEnabled(false);
        // Grafiği yeniler
        barChart.invalidate();
    }
}
