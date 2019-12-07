package cresla.io;

import cresla.interfaces.OutputWriter;

public class OutputWriterImpl implements OutputWriter {
    @Override
    public void write(String output) {
        System.out.println(output);
    }

    @Override
    public void writeLine(String output) {
        System.out.println(output);
    }
}
