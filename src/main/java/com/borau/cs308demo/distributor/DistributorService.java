package com.borau.cs308demo.distributor;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DistributorService {

    private final DistributorRepository distributorRepo;


    public List<Distributor> findAll() {
        return distributorRepo.findAll();
    }

    public Optional<Distributor> findById(String id) {
        return distributorRepo.findById(id);
    }

    public Distributor save(Distributor distributor) {
        return distributorRepo.save(distributor);
    }

    public void deleteById(String id) {
        distributorRepo.deleteById(id);
    }

    /*public Distributor update(String id, Distributor distributorDetails) {
        return distributorRepo.findById(id)
                .map(distributor -> {
                    distributor.setName(distributorDetails.getName());
                    distributor.setAddress(distributorDetails.getAddress());
                    distributor.setPhone(distributorDetails.getPhone());
                    distributor.setWebsite(distributorDetails.getWebsite());
                    distributor.setIsActive(distributorDetails.isActive());
                    return distributorRepo.save(distributor);
                })
                .orElseGet(() -> {
                    distributorDetails.setId(id);
                    return distributorRepo.save(distributorDetails);
                });
    }*/
}


