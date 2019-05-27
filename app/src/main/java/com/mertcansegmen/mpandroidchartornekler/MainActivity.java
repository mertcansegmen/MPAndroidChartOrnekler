package com.mertcansegmen.mpandroidchartornekler;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

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
        int[] indexlerLine = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

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
            degerlerLine.add(new BarEntry(indexlerLine[i], kazanclar[i]));
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
        lineChart.moveViewToX(indexlerLine.length*1f);

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
        float[] urunGetirileri = {1238.81f, 853.81f, 724.85f, 617.85f, 614.95f, 598.95f, 553.05f,
                        441.05f, 427.45f, 426.45f, 401.25f, 378.25f, 290.15f, 240.15f};
        // X ekseninin indexleri
        float[] indexlerBar = {0f, 1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f, 11f, 12f, 13f};
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
            degerlerBar.add(new BarEntry(indexlerBar[i], urunGetirileri[i]));
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
        barChart.animateY(1000);
        // PinchZoom aktif edilir. false ayarlanırsa X ve Y ekseni için ayrı ayrı yakınlaştırılır
        barChart.setPinchZoom(true);
        // Grafiğin sağ altındaki açıklamayı gizler
        barChart.getDescription().setEnabled(false);
        // Grafiği yeniler
        barChart.invalidate();

        //*************************************************************************************//

        PieChart pieChart = findViewById(R.id.pie_chart);

        List<PieEntry> degerlerPie = new ArrayList<>();
        degerlerPie.add(new PieEntry(23f, "Okan"));
        degerlerPie.add(new PieEntry(14f, "Veli"));
        degerlerPie.add(new PieEntry(35f, "Buse"));
        degerlerPie.add(new PieEntry(28f, "Osman"));

        PieDataSet dataSet = new PieDataSet(degerlerPie, "|  Çalışanların ürün satma yoğunluğu");
        // Dilim renkleri
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        // Dilimlerin arasındaki boşluğu ayarlar
        dataSet.setSliceSpace(6f);
        // Dilim seçilince ne kadar dışarı çıkacağını ayarlar
        dataSet.setSelectionShift(4f);

        PieData data = new PieData(dataSet);
        // Değerlerin yazı büyüklüğü
        data.setValueTextSize(10f);
        // Değerlerin yazı rengi
        data.setValueTextColor(Color.BLACK);
        pieChart.setData(data);

        // Etiketlerin yazı rengi
        pieChart.setEntryLabelColor(Color.BLACK);
        // Grafiğin sağ altındaki açıklamayı gizler
        pieChart.getDescription().setEnabled(false);
        // Grafiğin iç tarafının boş olmasını engeller
        pieChart.setDrawHoleEnabled(false);
        pieChart.spin(1000, 0f, 360f, Easing.EaseInOutQuad);
        // Grafiği yeniler
        pieChart.invalidate();

        //*************************************************************************************//

        BarChart multipleBarChart = findViewById(R.id.stacked_bar_chart);

        // Y ekseninde gözükecek değerler
        float[] karlar = {424.25f, 427.45f, 340.15f, 614.95f, 553.05f, 827.85f, 871.81f,
                324.25f, 527.45f, 540.15f, 314.95f, 653.05f, 854.85f, 771.81f};
        float[] cirolar = {2014.25f, 1984.45f, 1641.15f, 3291.95f, 2523.05f, 3827.85f, 4171.81f,
                1424.25f, 2027.45f, 2350.15f, 1214.95f, 3153.05f, 4554.85f, 3271.81f};

        // X ekseninin indexleri
        int[] indexlerMultipleBar = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

        // X ekseninde gözükecek değerler
        final String[] tarihlerMultipleBar = new String[]{"6 May", "7 May", "8 May", "9 May", "10 May", "11 May", "12 May",
                "13 May", "14 May", "15 May", "16 May", "17 May", "18 May", "19 May"};

        ValueFormatter formatterMultipleBar = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                if(tarihlerMultipleBar.length > (int) value)
                    return tarihlerMultipleBar[(int) value];
                else return "";
            }
        };

        List<BarEntry> karlarMultipleBar = new ArrayList<>();
        List<BarEntry> cirolarMultipleBar = new ArrayList<>();

        for(int i = 0; i<tarihlerMultipleBar.length; i++){
            karlarMultipleBar.add(new BarEntry(indexlerMultipleBar[i], karlar[i]));
            cirolarMultipleBar.add(new BarEntry(indexlerMultipleBar[i], cirolar[i]));
        }

        BarDataSet karlarBarDataSet = new BarDataSet(karlarMultipleBar, "Günlük Kar (₺)");
        // Bar rengi
        karlarBarDataSet.setColor(getResources().getColor(R.color.karRenk));
        BarDataSet cirolarBarDataSet = new BarDataSet(cirolarMultipleBar, "Günlük Ciro (₺)");
        // Bar rengi
        cirolarBarDataSet.setColor(getResources().getColor(R.color.ciroRenk));

        List<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(cirolarBarDataSet);
        dataSets.add(karlarBarDataSet);

        BarData multipleBarData = new BarData(dataSets);
        // Barların kalınlığını ayarlar
        multipleBarData.setBarWidth(0.35f);
        multipleBarChart.setData(multipleBarData);

        XAxis xEkseniMultipleBar = multipleBarChart.getXAxis();
        // X eksenindeki değerlerin aşağıda gözükmesini sağlar
        xEkseniMultipleBar.setPosition(XAxis.XAxisPosition.BOTTOM);
        // X ekseni için ızgara çizgilerini gizler
        xEkseniMultipleBar.setDrawGridLines(false);
        // Yakınlaştırılınca x ekseninde fazladan değer çıkmasını engeller
        xEkseniMultipleBar.setGranularity(1f);
        // X ekseninin başlangıcında boşluk bırakır
        xEkseniMultipleBar.setAxisMinimum(multipleBarData.getXMin() - 0.5f);
        // X ekseninin sonunda boşluk bırakır
        xEkseniMultipleBar.setAxisMaximum(multipleBarData.getXMax() + 0.5f);
        // Değer formatlayıcıyı ayarlar
        xEkseniMultipleBar.setValueFormatter(formatterMultipleBar);


        YAxis yEkseniSolMultipleBar = multipleBarChart.getAxisLeft();
        // sol Y ekseninde gözükecek değer sayısını ayarlar
        yEkseniSolMultipleBar.setLabelCount(5, false);
        // sol Y ekseninin göstereceği minimum değeri ayarlar
        yEkseniSolMultipleBar.setAxisMinimum(0f);

        YAxis yEkseniSagMultipleBar = multipleBarChart.getAxisRight();
        // sağ Y ekseninde gözükecek değer sayısını ayarlar
        yEkseniSagMultipleBar.setLabelCount(5, false);
        // sağ Y ekseninin göstereceği minimum değeri ayarlar
        yEkseniSagMultipleBar.setAxisMinimum(0f);
        // sağ Y ekseni için ızgara çizgilerini gizler çünkü sol Y ekseni zaten gösteriyor
        yEkseniSagMultipleBar.setDrawGridLines(false);
        // sağ Y ekseninin çizgisini gizler
        yEkseniSagMultipleBar.setDrawAxisLine(false);

        // X eksenindeki sadece 7 değerin gözükmesini sağlar
        multipleBarChart.setVisibleXRangeMaximum(7);
        // Tablonun en sondaki değerlerin gözükmesini sağlar
        multipleBarChart.moveViewToX(indexlerMultipleBar.length*1f);
        // Grafiğe çift tıkla yakınlaştırmayı kapatır
        multipleBarChart.setDoubleTapToZoomEnabled(false);
        // X eksenine animasyon uygulayarak grafiği 750 milisaniyede çizer
        multipleBarChart.animateX(750);
        // PinchZoom aktif edilir. false ayarlanırsa X ve Y ekseni için ayrı ayrı yakınlaştırılır
        multipleBarChart.setPinchZoom(true);
        // Grafiğin sağ altındaki açıklamayı siler
        multipleBarChart.getDescription().setEnabled(false);
        // Birden fazla bar varsa başlangıç noktasını, barlar arası uzaklığı ve bar grupları arası uzaklığı belirler
        multipleBarChart.groupBars(-0.5f, 0.14f, 0.08f);
        // Grafiği yeniler
        multipleBarChart.invalidate();

    }
}
