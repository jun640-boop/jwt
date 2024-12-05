package org.zerock.ex3.member.dto;

import jakarta.persistence.Id;
import lombok.*;
import org.zerock.ex3.member.entity.MemberEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDto {

    private String mid;

    private String mpw;

    private String mname;

    private String email;

    private LocalDateTime joinDate;

    private LocalDateTime modifiedDate;

    private String role;


    public Map<String, Object> getDataMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("mid", mid);
        map.put("mname", mname);
        map.put("email", email);
        map.put("role", role);
        return map;
    }

    public MemberDto(MemberEntity memberEntity) {
        this.mid = memberEntity.getMid();
        this.mpw = memberEntity.getMpw();
        this.email = memberEntity.getEmail();
        this.mname = memberEntity.getMname();
        this.joinDate = memberEntity.getJoinDate();
        this.modifiedDate = memberEntity.getJoinDate();
        this.role = memberEntity.getRole();
    }


}
