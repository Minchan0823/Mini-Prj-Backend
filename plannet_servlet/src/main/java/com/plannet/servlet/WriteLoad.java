package com.plannet.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.plannet.common.Common;
import com.plannet.dao.WriteDAO;
import com.plannet.vo.WriteVO;


@WebServlet("/WriteLoad")
public class WriteLoad extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.getWriter().append("Served at: ").append(request.getContextPath());
   }

   protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      Common.corsResSet(response);
   }

   @SuppressWarnings("unchecked")
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response = Common.corsResSet(response);

		StringBuffer sb = Common.reqStringBuff(request);
		JSONObject jsonObj = Common.getJsonObj(sb);
		
		System.out.println("id : " + (String)jsonObj.get("id"));
		String getId = (String)jsonObj.get("id");
		Date getDate = Date.valueOf((String) jsonObj.get("date"));
		
		PrintWriter out = response.getWriter();
		
		WriteDAO dao = new WriteDAO();
		List<WriteVO> list = dao.writeLoad(getId, getDate); 	
		
		JSONArray writeArray = new JSONArray();
		for(WriteVO e : list) {
			JSONObject resJson = new JSONObject();
			resJson.put("plan", e.getPlan());
			resJson.put("diary", e.getDiary());
			writeArray.add(resJson);
		}	
		out.print(writeArray);
   }
}