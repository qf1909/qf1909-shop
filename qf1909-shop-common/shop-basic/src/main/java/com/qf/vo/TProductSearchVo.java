package com.qf.vo;

import com.qf.dto.TProductSearchDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author smj
 * @date 2020/3/14 - 16:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TProductSearchVo {
    private List<TProductSearchDTO> list;
    private Integer pageNo;
}
