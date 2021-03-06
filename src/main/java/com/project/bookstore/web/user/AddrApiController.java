package com.project.bookstore.web.user;

import com.project.bookstore.config.ApiResponse;
import com.project.bookstore.service.users.AddrService;
import com.project.bookstore.session.UserInfo;
import com.project.bookstore.web.user.dto.addrDto.AddrInsertDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Api(value = "주소", description = "주소 관리", tags = { "주소" })
@RequestMapping("/users")
@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class AddrApiController {
    private final AddrService addrService;
    private final UserInfo userInfo;

    @ApiOperation(value = "주소지 등록")
    @PostMapping("/addr")
    public ResponseEntity<?> addrInsert(@RequestBody AddrInsertDto insertDto) {
        ApiResponse result = null;
        System.out.println(insertDto.getAddrYN());
        try {
            if(insertDto.getAddrZip() != null) {
                if(!insertDto.getAddrYN().equals("Y")) {
                    if(addrService.findAddr(userInfo).isEmpty()){
                        insertDto.setAddrYN("Y");
                    } else {
                        insertDto.setAddrYN(null);
                    }
                } else {
                    if (!addrService.findAddr(userInfo).isEmpty()){
                        addrService.addrUpdateYN(userInfo);
                    }
                    insertDto.setAddrYN("Y");
                }
                if (insertDto.getAddrNic() == null) {
                    insertDto.setAddrNic(insertDto.getAddrName());
                }
                insertDto.setUsers(addrService.findUsers(userInfo));
                result = new ApiResponse(true, "성공", addrService.addrSave(insertDto));
                return ResponseEntity.ok().body(result);
            } else {
                result = new ApiResponse(false, "실패", null);
                return ResponseEntity.badRequest().body(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    @ApiOperation(value = "주소지 수정")
    @PostMapping("/addr/update/{addrCode}")
    public ResponseEntity<?> addrUpdate(@PathVariable Long addrCode, @RequestBody AddrInsertDto insertDto) {
        ApiResponse result = null;
        System.out.println(insertDto);
        try {
            if (insertDto.getAddrZip() != null) {
                if (!insertDto.getAddrYN().equals("Y")) {
                    if (addrService.findAddr(userInfo).isEmpty()) {
                        insertDto.setAddrYN("Y");
                    } else {
                        insertDto.setAddrYN(null);
                    }
                } else {
                    if (!addrService.findAddr(userInfo).isEmpty()) {
                        addrService.addrUpdateYN(userInfo);
                    }
                    insertDto.setAddrYN("Y");
                }
                if (insertDto.getAddrNic() == null) {
                    insertDto.setAddrNic(insertDto.getAddrName());
                }
                insertDto.setUsers(addrService.findUsers(userInfo));
                addrService.addrUpdate(addrCode, insertDto);
                result = new ApiResponse(true, "성공", null);
                return ResponseEntity.ok().body(result);
            } else {
                result = new ApiResponse(false, "실패", null);
                return ResponseEntity.badRequest().body(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    @ApiOperation(value = "주소지 삭제")
    @PostMapping("/addr/delete/{addrCode}")
    public RedirectView addrDelete(@PathVariable Long addrCode) {
        addrService.addrDelete(addrCode);
        return new RedirectView("/users/mypage");

    }
}
