package com.java_dapps.store.service;

import com.topchain.node.model.viewModel.BlockViewModel;

import java.util.List;

public interface ItemService {
    List<BlockViewModel> getBlocks();

    BlockViewModel getBlockByIndex(long index);

    void updateBlockchain(List<BlockViewModel> blockViewModels);

    boolean peerBlocksAreValid(List<BlockViewModel> blockViewModels);
}
