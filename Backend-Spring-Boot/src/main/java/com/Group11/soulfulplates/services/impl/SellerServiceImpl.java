package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.repository.SellerRepository;
import com.Group11.soulfulplates.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
}
