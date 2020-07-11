package filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MsgpaneFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String objfile = request.getServletContext().getRealPath("/")+"msgpane\\Msgpane.obj";
		System.out.println(objfile);
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		ServletContext application = request.getServletContext();
		if (application.getAttribute("msgs") == null) {
			File f = new File(objfile);
			if (f.exists()) {
				try {
					application.setAttribute("msgs", readObject(objfile));
					System.out.println("read");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("File not found!");
				req.getRequestDispatcher("/msgpane/submit.jsp").forward(req,
						resp);
			}
		}
		chain.doFilter(req, resp);
		//執行下1個過濾器
		
		
		
		
		//servlet執行完後，呼叫下1個存檔
	}

	@Override
	public void destroy() {
	}

	public Object readObject(String filename) throws IOException,
			ClassNotFoundException {
		Object obj = null;
		File ft = new File(filename);
		if (ft.exists()) {
			FileInputStream fis = new FileInputStream(ft);
			ObjectInputStream ois = new ObjectInputStream(fis);
			obj = ois.readObject();
			fis.close();
			ois.close();
		} else {
			// do something
		}
		return obj;
	}
}
