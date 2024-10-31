package uriel.uam.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import uriel.uam.exception.CustomException;
import uriel.uam.exception.ErrorCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
@RequiredArgsConstructor
public class FaceScanService {

    @Value("${spring.exe-path}")
    private String exe;

    @Value("${spring.exe-directory}")
    private String exeDirectory;

    @Value("${spring.ino-file-name}")
    private String inoFileName;

    public void runExe() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(exe);
            // 실행 디렉토리 설정
            processBuilder.directory(new File(exeDirectory));
            // 스트림 병합 (출력 + 에러)
            processBuilder.redirectErrorStream(true);
            // 프로세스 시작
            Process process = processBuilder.start();
            // 프로세스가 종료될 때까지 기다림
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 얼굴 스캔 및 결과 리턴
    public int faceScanResult() {

        String passengerId = null;
        // 1. 실행
        runExe();

        // 2. 파일 읽기
        String fileLocation = exeDirectory + inoFileName;
        Path path = Paths.get(fileLocation);
        URI uri = path.toUri();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new UrlResource(uri).getInputStream()))) {
            while (true) {
                String line = br.readLine();
                if (line != null) {
                    passengerId = line;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (passengerId == null) {
            throw new CustomException(ErrorCode.FACE_SCAN_ERROR);
        }
        log.info("passengerId = {}", passengerId);
        return Integer.parseInt("3");
    }
}
