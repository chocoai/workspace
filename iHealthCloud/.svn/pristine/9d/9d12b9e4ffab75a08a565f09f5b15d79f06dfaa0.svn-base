
package com.yhcrt.iHealthCloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.iHealthCloud.entity.SysSequence;
import com.yhcrt.iHealthCloud.entity.SysSequenceExample;
import com.yhcrt.iHealthCloud.mapper.SysSequenceMapper;
import com.yhcrt.iHealthCloud.service.SysSequenceService;

/**
 * @author rpf
 *
 */
@Service("sysSequenceService")
public class SysSequenceServiceImpl implements SysSequenceService {

	@Autowired
	private SysSequenceMapper sequenceMapper;

	public SysSequenceServiceImpl() {
	}

	@Override
	public Integer getSequenceValue(String sequenceName) {
		SysSequenceExample example = new SysSequenceExample();
		example.createCriteria().andSequNameEqualTo(sequenceName);
		List<SysSequence> sequences = sequenceMapper.selectByExample(example);
		int sequenceValue = 0;
		if (!sequences.isEmpty()) {
			sequenceValue = sequences.get(0).getSequValue() + 1;
			SysSequence sequence = sequences.get(0);
			sequence.setSequValue(sequenceValue);
			sequenceMapper.updateByExample(sequence, example);
		}

		return sequenceValue;
	}

	

}
