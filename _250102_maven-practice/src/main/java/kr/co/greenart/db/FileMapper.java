package kr.co.greenart.db;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface FileMapper {
	@Insert("INSERT INTO fileupload (filename, filedata) VALUES (#{ filename }, #{ filedata })")
	int insert(@Param("filename") String filename
			,@Param("filedata") byte[] fileData);
}
