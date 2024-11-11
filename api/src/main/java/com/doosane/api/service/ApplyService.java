package com.doosane.api.service;

import com.doosane.api.domain.Coupon;
import com.doosane.api.producer.CouponCreateProducer;
import com.doosane.api.repository.CouponCountRepository;
import com.doosane.api.repository.CouponRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApplyService {

    private final CouponRepository couponRepository;

    private final CouponCountRepository couponCountRepository;

    private final CouponCreateProducer couponCreateProducer;

    public ApplyService(CouponRepository couponRepository, CouponCountRepository couponCountRepository, CouponCreateProducer couponCreateProducer) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
        this.couponCreateProducer = couponCreateProducer;
    }

    @Transactional
    @Modifying
    public void apply(Long userId) {
        long count = couponCountRepository.increment();

        if(count > 100) {
            return;
        }
       couponRepository.save(new Coupon(userId));
//       couponCreateProducer.create(userId);
    }

//    public CouponCreateProducer getCouponCreateProducer() {
//        return couponCreateProducer;
//    }
}
