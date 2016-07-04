package br.com.wmoreira.domains.mapper;

public interface Mapper<L, R> {
    R mapFromLeftObject(L left);
    L mapFromRightObject(R right);
}
