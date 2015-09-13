package br.com.jsflab.view.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean(name = "graficoMB")
@ViewScoped
public class GraficoMB extends AbstractMB{

    private static final long serialVersionUID = -8792718680062101806L;

    private LineChartModel graficoLinhas;

    @Override
    public void inicializar() {

        gerarGraficos();
    }

    private void gerarGraficos() {

        graficoLinhas = initLinearModel();
        graficoLinhas.setTitle("Line Chart");
        graficoLinhas.setAnimate(true);
        graficoLinhas.setLegendPosition("se");

        Axis yAxis = graficoLinhas.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
    }

    private LineChartModel initLinearModel() {

        LineChartModel model = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");

        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);

        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");

        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);

        model.addSeries(series1);
        model.addSeries(series2);

        return model;
    }


    public LineChartModel getGraficoLinhas() {

        return graficoLinhas;
    }


    public void setGraficoLinhas(LineChartModel graficoLinhas) {

        this.graficoLinhas = graficoLinhas;
    }

}
