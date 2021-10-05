package com.techelevator.auctions.controller;

import com.techelevator.auctions.dao.AuctionDao;
import com.techelevator.auctions.dao.MemoryAuctionDao;
import com.techelevator.auctions.model.Auction;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path ="/auctions")

public class AuctionController {

    private AuctionDao dao;

    public AuctionController() {
        this.dao = new MemoryAuctionDao();
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Auction> list(String title, @RequestParam (defaultValue = "", required = false) String title_like,
                              @RequestParam (defaultValue = "0.0", required = false) double currentBid_lte) {
        List<Auction> auctionList = dao.list();

        if(title_like != null && currentBid_lte > 0) {
            return dao.searchByTitleAndPrice(title_like, currentBid_lte);
        }else if(currentBid_lte > 0) {
            return dao.searchByPrice(currentBid_lte);
        }

        else if(title_like != null) {
            List<Auction> searchedList = dao.searchByTitle(title_like);

            return searchedList;
        }else {
            return auctionList;
        }

    }


    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Auction get(@PathVariable int id) {
        Auction auction = dao.get(id);

        return auction;
    }

    @RequestMapping(path="",method = RequestMethod.POST)
    public Auction create(@RequestBody Auction auction) {
        Auction newAuction = dao.create(auction);

        return newAuction;
    }


/*
    @RequestMapping(path="/filter", method = RequestMethod.GET)
    public List<Auction> searchByTitle(@RequestParam (defaultValue = "") String title_like) {

        if(title_like != null) {
            List<Auction> searchedList = dao.searchByTitle(title_like);

            return searchedList;
        }else {
            return getAllAuction();
        }

    }*/


//    @RequestMapping(path="/filter", method = RequestMethod.GET)
//    public List<Auction> searchByPrice(String title_like, @RequestParam (defaultValue = "0.0")  double currentBid_lte) {
//
//
//            List<Auction> searchedList = dao.searchByPrice(currentBid_lte);
//
//            return searchedList;
//
//    }




    }
