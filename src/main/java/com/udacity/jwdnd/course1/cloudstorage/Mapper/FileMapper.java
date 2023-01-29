package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.Model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE userId =#{userId}")
    List<File> getFilesByUser(Integer userId);
    @Select("SELECT * FROM FILES WHERE fileid = #{fileId}")
    File getByFile(Integer fileId);

    @Select("SELECT * FROM FILES WHERE filename = #{fileName}")
    File getFileByFileName(String fileName);

    @Insert("INSERT INTO FILES (filename, contenttype , filesize, userid, filedata) VALUES (#{fileName}, #{contentType} , #{fileSize}, #{userId},#{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int uploadFile(File file);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    int delete(@Param("fileId") int fileId);
}
