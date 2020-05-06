package cn.qin.service;

import cn.qin.dao.repository.ApplyJobRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ApplyJobService {
    @Autowired
    private ApplyJobRepository applyJobRepository;

}
