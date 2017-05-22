package com.westernacher.model.transformer;

public interface BaseTransformer<K, V> {
	K transformToDomain(V restModel);
	V transformToRest(K domainModel);
}
