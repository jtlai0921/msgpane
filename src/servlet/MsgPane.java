package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MsgPane extends HttpServlet {
	List<String> msgs;
	AtomicInteger count=new AtomicInteger();
	String objfile="";
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		objfile=request.getServletContext().getRealPath("/")+"msgpane\\Msgpane.obj";
		System.out.println(objfile);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ServletContext application = request.getServletContext();
		HttpSession session=request.getSession();
		//判斷訊息為空值?
		if(application.getAttribute("msgs")!=null){
			msgs=(List<String>)application.getAttribute("msgs");
		}else{
			msgs=(List<String>) Collections.synchronizedCollection(new ArrayList()) ;
			System.out.println("New");
		}
		//吃記憶體要清掉
		count.set(msgs.size());
		String date = new Date().toLocaleString();
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String msg = request.getParameter("message");
		if (name == null || "".equals(name)) {
			name = "guest";
		}
		if (title == null || "".equals(title)) {
			title = " ";
		}
		if (msg == null || "".equals(msg)) {
			System.out.println("123");
			request.getRequestDispatcher("/msgpane/submit.jsp").forward(request,
					response);
		} else {
			System.out.println("1s23");
			count.incrementAndGet();
			String tmsg = "<br><br><br>No." + count + "&nbsp;&nbsp;&nbsp;"
					+ date + "<br><br>姓名" + name + "<br>" + title
					+ "<br>留言" + msg;
			msgs.add(tmsg);
			saveMsgPane(objfile);	
			//存檔要寫在呼叫下個filter之後
			application.setAttribute("msgs", msgs);
			session.setAttribute("msg", tmsg);
			request.getRequestDispatcher("/msgpane/msgpane.jsp").forward(request,
					response);			
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

	public void saveMsgPane(String filename) {
		try {
			saveObject(filename, msgs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveObject(String filename, Object obj) throws IOException {
		File f = new File(filename);
		if (!f.exists()) {
			f.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(f);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj);
		fos.close();
		oos.close();
	}
}
