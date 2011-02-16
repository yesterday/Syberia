package yester.day.syberia.utils;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Documentation
 * <p/>
 *
 * @author yesterday
 * @since 13 feb 2011  9:33:00
 */
public class RestrictedInputStream extends FilterInputStream {
    int skiped, read, limit;

    public RestrictedInputStream(InputStream in, int limit) {
        super(in);
        this.limit = limit;
    }

    @Override
    public int read() throws IOException {
        if (read > limit) throw new IOException("No more your data!");
        final int i = super.read();
        if (i > 0) read++;
        return i;
    }

    @Override
    public int read(byte[] b) throws IOException {
        if (read + b.length > limit) throw new IOException("No more tears");
        final int r = super.read(b);
        read += r;
        return r;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (read + len > limit) throw new IOException("No more tears");
        final int r = super.read(b, off, len);
        read += r;
        return r;

    }

    @Override
    public long skip(long len) throws IOException {
        if (read + len > limit) throw new IOException("No more tears");
        final long r = super.skip(len);
        read += r;
        skiped += r;
        return r;
    }

    @Override
    public int available() throws IOException {
        return  limit - read;
    }

    @Override
    public void close() throws IOException {
        skip(available());
    }

    public int getSkiped() {
        return skiped;
    }

    public int getReaden() {
        return read;
    }
}
