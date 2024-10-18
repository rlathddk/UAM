package uriel.uam.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uriel.uam.response.ResResult;
import uriel.uam.response.ResponseCode;
import uriel.uam.service.FaceScanService;

@RestController
@RequestMapping("/face-scan")
@RequiredArgsConstructor
public class FaceScanController {

    private final FaceScanService faceScanService;

    @GetMapping
    public ResponseEntity<ResResult> faceScan() {
        int id = faceScanService.faceScanResult();
        ResponseCode responseCode = ResponseCode.FACE_SCAN_SUCCESSFUL;
        return ResponseEntity.ok(
                ResResult.builder()
                        .responseCode(responseCode)
                        .code(responseCode.getCode())
                        .message(responseCode.getMessage())
                        .data(id)
                        .build()
        );
    }
}
