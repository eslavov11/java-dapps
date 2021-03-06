package com.distributedgame.dataaccess.serviceimpl;

import com.distributedgame.dataaccess.entity.Kingdom;
import com.distributedgame.dataaccess.model.viewmodel.KingdomViewModel;
import com.distributedgame.dataaccess.repository.KingdomRepository;
import com.distributedgame.dataaccess.service.KingdomService;
import com.distributedgame.dataaccess.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class KingdomServiceImpl implements KingdomService {
    private static final int MIN_COORD = 0;
    private static final int MAX_COORD = 100;

    private KingdomRepository repository;
    private ModelMapper modelMapper;
    private UserService userService;

    @Autowired
    public KingdomServiceImpl(ModelMapper modelMapper, KingdomRepository repository, UserService userService) {
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public List<KingdomViewModel> createForUser(long userId) {
        List<KingdomViewModel> kingdomViewModels = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Kingdom kingdom = new Kingdom();
            kingdom.setUser(this.userService.getById(userId));
            generateKingdomCoordinates(kingdom);

            this.repository.saveAndFlush(kingdom);
            KingdomViewModel kingdomViewModel = this.modelMapper
                    .map(kingdom, KingdomViewModel.class);
            kingdomViewModels.add(kingdomViewModel);
        }

        return kingdomViewModels;
    }

    private void generateKingdomCoordinates(Kingdom kingdom) {
        do {
            kingdom.setXCoord(ThreadLocalRandom.current()
                    .nextInt(MIN_COORD, MAX_COORD + 1));
            kingdom.setYCoord(ThreadLocalRandom.current()
                    .nextInt(MIN_COORD, MAX_COORD + 1));
        } while (this.repository
                .findByXCoordAndYCoord(kingdom.getXCoord(), kingdom.getYCoord())
                .isPresent());
    }

    @Override
    public List<KingdomViewModel> getAllForUser(long userId) {
        List<KingdomViewModel> kingdomViewModels = new ArrayList<>();
        this.repository.findAllByUserId(userId).forEach((Kingdom k) -> {
            KingdomViewModel kingdomViewModel = this.modelMapper.map(k, KingdomViewModel.class);
            kingdomViewModels.add(kingdomViewModel);
        });

        return kingdomViewModels;
    }
}
