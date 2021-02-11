package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileStreamTest {

    private String local_path = "C:\\upload\\";

    private String file_name = "/file_test.txt";

    private File file = new File(local_path + file_name);

    @Test
    @DisplayName("디렉토리 생성")
    public void create_dir() {
        File file = new File(local_path);
        if (file.isDirectory()) {
            //상위 디렉토리가 없으면 같이 만들어줌
            file.mkdirs();
        }
    }

    @Test
    @DisplayName("파일 생성")
    public void create_file() throws IOException {
        //파일이 존재 하는지 확인
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    @Test
    @DisplayName("파일 삭제")
    public void delete_file() {
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    @DisplayName("바이트 단위로 작성")
    public void byte_write_file() throws IOException {
        create_file();

        //파일 존재시 덮어씌움
        //byte stream은 1 byte씩 처리
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write("test".getBytes(StandardCharsets.UTF_8));
        fileOutputStream.close();
    }

    @Test
    @DisplayName("문자 단위로 작성")
    public void char_write_file() throws IOException {
        create_file();

        //파일 존재시 덮어씌움
        //한글이나 문자관련 처리를 할때 사용
        //2 byte씩 처리
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("테스트");
        fileWriter.flush();
        fileWriter.close();
    }
}
