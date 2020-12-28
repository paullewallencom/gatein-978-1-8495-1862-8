package org.exoplatform.sample.portal.web;

import org.exoplatform.web.filter.Filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SampleFilter implements Filter
{

   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
      ServletException
   {
      System.out.println("SampleFilter start of the 'financials-portal'");
      try
      {
         chain.doFilter(request, response);
      }
      finally
      {
         System.out.println("SampleFilter end of the 'financials-portal'");
      }
   }

}
