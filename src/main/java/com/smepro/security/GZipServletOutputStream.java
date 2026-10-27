package com.smepro.security;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;

class GZipServletOutputStream extends ServletOutputStream {
	  private GZIPOutputStream    gzipOutputStream = null;

	  public GZipServletOutputStream(OutputStream output)
	        throws IOException {
	    super();
	    this.gzipOutputStream = new GZIPOutputStream(output);
	  }

	  @Override
	  public void close() throws IOException {
	    this.gzipOutputStream.close();
	  }

	  @Override
	  public void flush() throws IOException {
	    this.gzipOutputStream.flush();
	  }

	  @Override
	  public void write(byte b[]) throws IOException {
	    this.gzipOutputStream.write(b);
	  }

	  @Override
	  public void write(byte b[], int off, int len) throws IOException {
	    this.gzipOutputStream.write(b, off, len);
	  }

	  @Override
	  public void write(int b) throws IOException {
	     this.gzipOutputStream.write(b);
	  }
	  
	// เพิ่ม 2 ตัวนี้เข้าไปเพื่อให้มันรองรับ Tomcat รุ่นใหม่ครับ

	    @Override
	    public boolean isReady() {
	        return true; 
	    }

	    @Override
	    public void setWriteListener(javax.servlet.WriteListener writeListener) {
	        // ไม่ต้องทำอะไร ปล่อยว่างไว้
	    }
	  
	}