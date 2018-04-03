package com.distributedgame.dataaccess.service;

import com.distributedgame.dataaccess.model.viewmodel.KingdomViewModel;

import java.util.List;

public interface KingdomService {
    List<KingdomViewModel> createForUser(long userId);

    List<KingdomViewModel> getAllForUser(long userId);
}
