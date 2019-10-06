/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

package com.wzh.datacenter.dao;

import com.wzh.datacenter.dao.provider.VoteSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author Wzh
 * @since 2019-10-01
 */
@Mapper
@Repository
public interface VoteMapper {

	/**
	 * 添加投票
	 * @param paraMap
	 * @return
	 */
	@InsertProvider(type = VoteSqlProvider.class, method = "insert")
	int addVote(@Param("vote") Map<String, Object> paraMap);

	/**
	 * 修改投票
	 * @param paraMap
	 * @return
	 */
	@UpdateProvider(type = VoteSqlProvider.class, method = "update")
	int updateVote(@Param("vote") Map<String, Object> paraMap);

	/**
	 * 删除投票
	 * @param paraMap
	 * @return
	 */
	@DeleteProvider(type = VoteSqlProvider.class, method = "delete")
	int deleteVote(@Param("vote") Map<String, Object> paraMap);

}
