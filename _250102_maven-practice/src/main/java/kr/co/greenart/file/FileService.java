package kr.co.greenart.file;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;

import jakarta.servlet.http.Part;
import kr.co.greenart.db.FileMapper;
import kr.co.greenart.util.DBUtil;

public class FileService {
	public int fileUpload(Part part) throws IOException {
		String fileName = part.getSubmittedFileName();
		byte[] filedata = part.getInputStream().readAllBytes();
		
		try (SqlSession session = DBUtil.getSqlSession()) {
			FileMapper mapper = session.getMapper(FileMapper.class);
			
			int row = mapper.insert(fileName, filedata);
			session.commit();
			return row;
		}
	}
}
