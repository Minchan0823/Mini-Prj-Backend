package com.plannet.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.plannet.common.Common;
import com.plannet.dao.MemoDAO;


@WebServlet("/MemberMemoSave")
public class MemberMemoSave extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.getWriter().append("Served at: ").append(request.getContextPath());
   }

   protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      Common.corsResSet(response);
   }

//   @SuppressWarnings("unchecked")
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      Common.corsResSet(response);
      StringBuffer sb = Common.reqStringBuff(request);
      JSONObject jsonObj = Common.getJsonObj(sb);
      String getId = (String)jsonObj.get("id");
      String getMemo = (String)jsonObj.get("memo");
      
      MemoDAO dao = new MemoDAO();
      dao.memberMemoSave(getId, getMemo);
   }
}