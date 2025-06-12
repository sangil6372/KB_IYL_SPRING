package org.scoula.board.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.board.domain.BoardVO;

@Data                    // getter, setter, toString, equals, hashCode 생성
@NoArgsConstructor       // 기본 생성자
@AllArgsConstructor      // 모든 필드 생성자
@Builder                 // 빌더 패턴
public class BoardDTO {
    private Long no;           // 게시글 번호
    private String title;      // 제목
    private String content;    // 내용
    private String writer;     // 작성자
    private Date regDate;      // 등록일시
    private Date updateDate;   // 수정일시

    private int viewCount;        // 조회수 (테이블에 없어도 됨)
    private List<String> tags;    // 태그 목록
    private boolean isOwner;      // 작성자 여부

    public static BoardDTO of(BoardVO vo) {
            return vo == null ? null : BoardDTO.builder()
                .no(vo.getNo())
                .title(vo.getTitle())
                .content(vo.getContent())
                .writer(vo.getWriter())
                .regDate(vo.getRegDate())
                .updateDate(vo.getUpdateDate())
                .build();
        }

    public BoardVO toVo() {
        return BoardVO.builder()
                .no(no)                    // this.no와 동일
                .title(title)              // this.title과 동일
                .content(content)
                .writer(writer)
                .regDate(regDate)
                .updateDate(updateDate)
                .build();
    }
}