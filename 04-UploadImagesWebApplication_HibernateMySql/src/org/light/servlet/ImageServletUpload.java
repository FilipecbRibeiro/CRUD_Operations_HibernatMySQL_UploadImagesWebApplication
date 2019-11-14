package org.light.servlet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.light.hibernate.data_acess_objec.FilesDAO;
import org.light.hibernate.entity.Files;

@WebServlet("/ImageUpload")
public class ImageServletUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String path = "D:/Organizado/Java/Images/";

	/////////////////////////////////////////////////////////////////////////////// SERVELT
	/////////////////////////////////////////////////////////////////////////////// METHODS///////////////////////////////////
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String param = request.getParameter("param");
		param = param.toLowerCase();
		System.out.println("--->" + param);
		switch (param) {

		case "listingimages":
			listingImages(request, response);
			break;
		case "actionimage":
			System.out.println("im here");
			viewImage(request, response);
			break;
		case "deleteimage":
			deleteImages(request,response);
			break;
		
		default:
			// errorPage(request,response);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
		}

	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String param = request.getParameter("param");
		param = param.toLowerCase();

		switch (param) {

		case "filesupload":
			filesUpload(request, response);
			break;
		case "updatefield":
			updateInformation(request, response);
			break;
		default:
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
		}
	}

	////////////////////////////////////////////////////////////////////// GET
	////////////////////////////////////////////////////////////////////// RELATED
	////////////////////////////////////////////////////////////////////// METHODS/////////////////////////////////////////////////////////////

	private void listingImages(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Files> files = new FilesDAO().getList();
		request.setAttribute("listOfFiles", files);
		request.setAttribute("path", path);
		request.getRequestDispatcher("listingFiles.jsp").forward(request, response);
		return;

	}

	private void viewImage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("fileId"));
		Files file = new FilesDAO().getFile(id);
		request.setAttribute("file", file);
		request.setAttribute("path", path);
		request.getRequestDispatcher("viewImage.jsp").forward(request, response);
		System.out.println(file);
	}
	private void deleteImages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	//Delete from DataBase!	
	int fileId= Integer.parseInt(request.getParameter("fileId"));
	Files file = new FilesDAO().getFile(fileId);
	new FilesDAO().deleteFile(fileId);
	//Delete from FileSystem
	File fileDisk= new File(path+file.getFileName());
	if(fileDisk.delete()) {
		System.out.println("File "+ file.getFileName()+" got deleted!");
	}else
		System.out.println("Error deleting ");
	
		listingImages(request, response);
	}
	////////////////////////////////////////////////////////////////////// POST
	////////////////////////////////////////////////////////////////////// RELATED
	////////////////////////////////////////////////////////////////////// METHODS/////////////////////////////////////////////////////////////
	public void filesUpload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
		try {
			List<FileItem> images = upload.parseRequest(request);
			for (FileItem run : images) {
				String name = run.getName();
				name = name.substring(name.lastIndexOf("\\") + 1);
				System.out.println(name);
				// NOTE -----> To prevent the existence of a repeated file we will see if it
				// exists or not!
				File file = new File(path + name);
				if (!file.exists()) {
					//// WRITE ON DATABASE //////////////
					new FilesDAO().addFiles(new Files(name));
					///// WRITE ON FOLDER /////////////////////
					run.write(file);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		listingImages(request, response);
	}

	private void updateInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int fieldId;
		fieldId = Integer.parseInt(request.getParameter("fileId"));
		String label = request.getParameter("label");
		String caption = request.getParameter("caption");
		new FilesDAO().updateInformation(fieldId, label, caption);
		listingImages(request, response);
	}
}
