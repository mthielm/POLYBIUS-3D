package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polyhedron;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Timer;

public class STLFileProcessor
{
    public Polyhedron processFile(String filePath)
    {
        Timer timer = new Timer();
        timer.start();

        STLReaderThread readerThread = new STLReaderThread(filePath);
        SurfaceCalculatorThread calculatorThread = new SurfaceCalculatorThread();

        readerThread.start();
        calculatorThread.start();

        try
        {
            readerThread.join();
            calculatorThread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        Polyhedron polyhedron = readerThread.getPolyhedron();
        PolyhedronController.getInstance().calculateVolume(polyhedron);
        return polyhedron;
    }
}