/*
 * CREDIT SUISSE IS WILLING TO LICENSE THIS SPECIFICATION TO YOU ONLY UPON THE
 * CONDITION THAT YOU ACCEPT ALL OF THE TERMS CONTAINED IN THIS AGREEMENT.
 * PLEASE READ THE TERMS AND CONDITIONS OF THIS AGREEMENT CAREFULLY. BY
 * DOWNLOADING THIS SPECIFICATION, YOU ACCEPT THE TERMS AND CONDITIONS OF THE
 * AGREEMENT. IF YOU ARE NOT WILLING TO BE BOUND BY IT, SELECT THE "DECLINE"
 * BUTTON AT THE BOTTOM OF THIS PAGE. Specification: JSR-354 Money and Currency
 * API ("Specification") Copyright (c) 2012-2013, Credit Suisse All rights
 * reserved.
 */
package javax.money.spi;

import java.util.Set;

import javax.money.CurrencyUnit;
import javax.money.MonetaryContext;
import javax.money.MonetaryOperator;

/**
 * This SPI allows to extends/override the roundings available for
 * {@link CurrencyUnit}. The JSRs implementation already provides default
 * roundings. By registering instances of this interface using the
 * {@link javax.money.spi.Bootstrap}, the default behaviour can be
 * overridden and extended, e.g. for supporting also special roundings.
 * <p>
 * Implementations of this interface must be
 * <ul>
 * <li>thread-safe
 * <li>not require loading of other resources.
 * </ul>
 * If required, it is possible to implement this interface in a contextual way,
 * e.g. providing different roundings depending on the current EE application
 * context. Though in most cases rounding should be a general concept that does
 * not require contextual handling.
 * 
 * @author Anatole Tresch
 */
public interface RoundingProviderSpi {

	/**
	 * Access the current valid rounding for the given {@link CurrencyUnit}.
	 * <p>
	 * Instances of {@link MonetaryOperator} returned, must be thread safe and
	 * immutable.
	 * 
	 * @param currency
	 *            the currency for which a rounding operator should be obtained,
	 *            not {@code null}.
	 * @return the corresponding rounding instance, or {@code null}.
	 */
	MonetaryOperator getRounding(CurrencyUnit currency);

	/**
	 * Access the rounding for the given {@link CurrencyUnit}, that was valid at
	 * the given timestamp.
	 * <p>
	 * Instances of {@link MonetaryOperator} returned, must be thread safe and
	 * immutable.
	 * 
	 * @param currency
	 *            the currency for which a rounding operator should be obtained,
	 *            not {@code null}.
	 * @param timestamp
	 *            the target UTC timestamp, when the rounding should be valid.
	 * @return the corresponding rounding instance, or {@code null}.
	 */
	MonetaryOperator getRounding(CurrencyUnit currency, long timestamp);

	/**
	 * Access the current valid rounding for the given {@link CurrencyUnit}.
	 * <p>
	 * Instances of {@link MonetaryOperator} returned, must be thread safe and
	 * immutable.
	 * 
	 * @param currency
	 *            the currency for which a rounding operator should be obtained,
	 *            not {@code null}.
	 * @return the corresponding rounding instance, or {@code null}.
	 */
	MonetaryOperator getCashRounding(CurrencyUnit currency);

	/**
	 * Access the cash rounding for the given {@link CurrencyUnit}, that was
	 * valid at the given timestamp.
	 * <p>
	 * Instances of {@link MonetaryOperator} returned, must be thread safe and
	 * immutable.
	 * 
	 * @param currency
	 *            the currency for which a rounding operator should be obtained,
	 *            not {@code null}.
	 * @param timestamp
	 *            the target UTC timestamp, when the rounding should be valid.
	 * @return the corresponding rounding instance, or {@code null}.
	 */
	MonetaryOperator getCashRounding(CurrencyUnit currency, long timestamp);

	/**
	 * Access an {@link MonetaryOperator} for custom rounding
	 * {@link javax.money.MonetaryAmount} instances.
	 * 
	 * @param customRoundingId
	 *            The customRounding identifier.
	 * @return the corresponding {@link MonetaryOperator} implementing the
	 *         rounding, or {@code null}.
	 */
	MonetaryOperator getCustomRounding(String customRoundingId);

	/**
	 * Access a rounding that models the given {@link MonetaryContext}.
	 * 
	 * @param monetaryContext
	 *            The target {@link MonetaryContext}
	 * @return the corresponding rounding, or {@code null}
	 */
	MonetaryOperator getRounding(MonetaryContext monetaryContext);

	/**
	 * Access the ids of the custom roundigs defined by this provider.
	 * 
	 * @return the ids of the defined custom roundings, never {@code null}.
	 */
	Set<String> getCustomRoundingIds();

}
