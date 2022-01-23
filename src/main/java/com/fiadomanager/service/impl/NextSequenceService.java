package com.fiadomanager.service.impl;

import com.fiadomanager.models.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
public class NextSequenceService {

    @Autowired
    private MongoOperations mongo;

    public Long getNextSequenceProduct(String seqName) {
        CustomSequencesProduct counter = mongo.findAndModify(
                query(where("_id").is(seqName)),
                new Update().inc("seq", 1),
                options().returnNew(true).upsert(true),
                CustomSequencesProduct.class);
        return counter.getSeq();
    }

    public Long getNextSequenceClient(String seqName) {
        CustomSequencesClient counter = mongo.findAndModify(
                query(where("_id").is(seqName)),
                new Update().inc("seq", 1),
                options().returnNew(true).upsert(true),
                CustomSequencesClient.class);
        return counter.getSeq();
    }

    public Long getNextSequenceUsers(String seqName) {
        CustomSequencesUsers counter = mongo.findAndModify(
                query(where("_id").is(seqName)),
                new Update().inc("seq", 1),
                options().returnNew(true).upsert(true),
                CustomSequencesUsers.class);
        return counter.getSeq();
    }

    public Long getNextSequenceOrderSheet(String seqName) {
        CustomSequencesOrderSheet counter = mongo.findAndModify(
                query(where("_id").is(seqName)),
                new Update().inc("seq", 1),
                options().returnNew(true).upsert(true),
                CustomSequencesOrderSheet.class);
        return counter.getSeq();
    }

    public Long getNextSequenceOrderSheetProduct(String seqName) {
        CustomSequencesOrderSheetProduct counter = mongo.findAndModify(
                query(where("_id").is(seqName)),
                new Update().inc("seq", 1),
                options().returnNew(true).upsert(true),
                CustomSequencesOrderSheetProduct.class);
        return counter.getSeq();
    }

}
