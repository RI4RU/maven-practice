package kr.co.greenart.file;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/file/upload")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
	private FileService service = new FileService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/fileForm.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Part part = req.getPart("upload");
		System.out.println(part.getSubmittedFileName());
		System.out.println(part.getSize());
		
		service.fileUpload(part);
		
//		part 객체 write 활용
//		part.write("d:\\userupload\\" + part.getSubmittedFileName());
		
//		직접 output스트림 제어하기
//		byte[] allBytes = part.getInputStream().readAllBytes();
//		
//		File saveFolder = new File("d:\\userupload");
//		saveFolder.mkdir();
//		File saveFile = new File(saveFolder, part.getSubmittedFileName());
//		
//		try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(saveFile))) {
//			bos.write(allBytes);
//		}
	}
}
