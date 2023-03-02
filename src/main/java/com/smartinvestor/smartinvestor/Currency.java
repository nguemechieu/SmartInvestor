package com.smartinvestor.smartinvestor;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents some currency. Could be a fiat currency issued by a country or a crypto-currency.
 *
 * @author Michael Ennen
 */
public class Currency {
     CurrencyType currencyType;
 String fullDisplayName;
     String shortDisplayName;
 String code;
int fractionalDigits;
 String symbol;
    private static final Map<SymmetricPair<String, CurrencyType>, Currency> CURRENCIES = new ConcurrentHashMap<>();
    public static final CryptoCurrency NULL_CRYPTO_CURRENCY = new NullCryptoCurrency();
    public static final FiatCurrency NULL_FIAT_CURRENCY = new NullFiatCurrency();
    private static final Logger logger = LoggerFactory.getLogger(Currency.class);

    static {
        // FIXME: Replace with ServiceLoaders
        CryptoCurrencyDataProvider cryptoCurrencyDataProvider = new CryptoCurrencyDataProvider();
        cryptoCurrencyDataProvider.registerCurrencies();
        FiatCurrencyDataProvider fiatCurrencyDataProvider = new FiatCurrencyDataProvider();
        fiatCurrencyDataProvider.registerCurrencies();

        /*
        ServiceLoader<CurrencyDataProvider> serviceLoader = ServiceLoader.load(CurrencyDataProvider.class);
        logger.info("service loader: " + serviceLoader);
        for (CurrencyDataProvider provider : serviceLoader) {
            logger.info("calling provider.registerCurrencies()");
            try {
                provider.registerCurrencies();
            } catch (Exception e) {
                logger.error("could not register currencies: ", e);
            }
        }
         */
    }

    /**
     * Private constructor used only for the {@code NULL_CURRENCY}.
     */
    protected Currency() {
        this.currencyType = CurrencyType.NULL;
        this.fullDisplayName = "";
        this.shortDisplayName = "";
        this.code = "XXX";
        this.fractionalDigits = 0;
        this.symbol = "";
    }

    /**
     * Protected constructor, called only by CurrencyDataProvider's.
     */
    protected Currency(CurrencyType currencyType, String fullDisplayName, String shortDisplayName, String code,
                       int fractionalDigits, String symbol) {
        Objects.requireNonNull(currencyType, "currencyType must not be null");
        Objects.requireNonNull(fullDisplayName, "fullDisplayName must not be null");
        Objects.requireNonNull(shortDisplayName, "shortDisplayName must not be null");
        Objects.requireNonNull(code, "code must not be null");

        if (fractionalDigits < 0) {
            throw new IllegalArgumentException("fractional digits must be non-negative, was: " + fractionalDigits);
        }
        Objects.requireNonNull(symbol, "symbol must not be null");

        this.currencyType = currencyType;
        this.fullDisplayName = fullDisplayName;
        this.shortDisplayName = shortDisplayName;
        this.code = code;
        this.fractionalDigits = fractionalDigits;
        this.symbol = symbol;
    }
   String id ;

    String name;
    String image;
    String currentPrice;
    String marketCap;
    String marketCapRank;
    String fullyDilutedValuation;
    String totalVolume;
    String high24h;
    String low24h;
    String priceChange24h;
    String priceChangePercentage24h;
    String marketCapChange24h;
    String marketCapChangePercentage24h;
    String circulatingSupply;
    String totalSupply;
    String maxSupply;
    String ath;
    String athChangePercentage;
    String atl;
    String atlChangePercentage;
    String atlDate;
    String roi;
    String lastUpdated;

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currencyType=" + currencyType +
                ", fullDisplayName='" + fullDisplayName + '\'' +
                ", shortDisplayName='" + shortDisplayName + '\'' +
                ", code='" + code + '\'' +
                ", fractionalDigits=" + fractionalDigits +
                ", symbol='" + symbol + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", currentPrice='" + currentPrice + '\'' +
                ", marketCap='" + marketCap + '\'' +
                ", marketCapRank='" + marketCapRank + '\'' +
                ", fullyDilutedValuation='" + fullyDilutedValuation + '\'' +
                ", totalVolume='" + totalVolume + '\'' +
                ", high24h='" + high24h + '\'' +
                ", low24h='" + low24h + '\'' +
                ", priceChange24h='" + priceChange24h + '\'' +
                ", priceChangePercentage24h='" + priceChangePercentage24h + '\'' +
                ", marketCapChange24h='" + marketCapChange24h + '\'' +
                ", marketCapChangePercentage24h='" + marketCapChangePercentage24h + '\'' +
                ", circulatingSupply='" + circulatingSupply + '\'' +
                ", totalSupply='" + totalSupply + '\'' +
                ", maxSupply='" + maxSupply + '\'' +
                ", ath='" + ath + '\'' +
                ", athChangePercentage='" + athChangePercentage + '\'' +
                ", atl='" + atl + '\'' +
                ", atlChangePercentage='" + atlChangePercentage + '\'' +
                ", atlDate='" + atlDate + '\'' +
                ", roi='" + roi + '\'' +
                ", lastUpdated='" + lastUpdated + '\'' +
                '}';
    }

    public void setFullDisplayName(String fullDisplayName) {
        this.fullDisplayName = fullDisplayName;
    }

    public void setShortDisplayName(String shortDisplayName) {
        this.shortDisplayName = shortDisplayName;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setFractionalDigits(int fractionalDigits) {
        this.fractionalDigits = fractionalDigits;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    public String getMarketCapRank() {
        return marketCapRank;
    }

    public void setMarketCapRank(String marketCapRank) {
        this.marketCapRank = marketCapRank;
    }

    public String getFullyDilutedValuation() {
        return fullyDilutedValuation;
    }

    public void setFullyDilutedValuation(String fullyDilutedValuation) {
        this.fullyDilutedValuation = fullyDilutedValuation;
    }

    public String getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(String totalVolume) {
        this.totalVolume = totalVolume;
    }

    public String getHigh24h() {
        return high24h;
    }

    public void setHigh24h(String high24h) {
        this.high24h = high24h;
    }

    public String getLow24h() {
        return low24h;
    }

    public void setLow24h(String low24h) {
        this.low24h = low24h;
    }

    public String getPriceChange24h() {
        return priceChange24h;
    }

    public void setPriceChange24h(String priceChange24h) {
        this.priceChange24h = priceChange24h;
    }

    public String getPriceChangePercentage24h() {
        return priceChangePercentage24h;
    }

    public void setPriceChangePercentage24h(String priceChangePercentage24h) {
        this.priceChangePercentage24h = priceChangePercentage24h;
    }

    public String getMarketCapChange24h() {
        return marketCapChange24h;
    }

    public void setMarketCapChange24h(String marketCapChange24h) {
        this.marketCapChange24h = marketCapChange24h;
    }

    public String getMarketCapChangePercentage24h() {
        return marketCapChangePercentage24h;
    }

    public void setMarketCapChangePercentage24h(String marketCapChangePercentage24h) {
        this.marketCapChangePercentage24h = marketCapChangePercentage24h;
    }

    public String getCirculatingSupply() {
        return circulatingSupply;
    }

    public void setCirculatingSupply(String circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    public String getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(String totalSupply) {
        this.totalSupply = totalSupply;
    }

    public String getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(String maxSupply) {
        this.maxSupply = maxSupply;
    }

    public String getAth() {
        return ath;
    }

    public void setAth(String ath) {
        this.ath = ath;
    }

    public String getAthChangePercentage() {
        return athChangePercentage;
    }

    public void setAthChangePercentage(String athChangePercentage) {
        this.athChangePercentage = athChangePercentage;
    }

    public String getAtl() {
        return atl;
    }

    public void setAtl(String atl) {
        this.atl = atl;
    }

    public String getAtlChangePercentage() {
        return atlChangePercentage;
    }

    public void setAtlChangePercentage(String atlChangePercentage) {
        this.atlChangePercentage = atlChangePercentage;
    }

    public String getAtlDate() {
        return atlDate;
    }

    public void setAtlDate(String atlDate) {
        this.atlDate = atlDate;
    }

    public String getRoi() {
        return roi;
    }

    public void setRoi(String roi) {
        this.roi = roi;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
    public Currency(String id, String symbol, String name, String image, String currentPrice, String marketCap, String marketCapRank, String fullyDilutedValuation, String totalVolume, String high24h, String low24h, String priceChange24h, String priceChangePercentage24h, String marketCapChange24h, String marketCapChangePercentage24h, String circulatingSupply, String totalSupply, String maxSupply, String ath, String athChangePercentage, String atl, String atlChangePercentage, String atlDate, String roi, String lastUpdated) {
        this.fractionalDigits = 8;
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.image = image;
        this.currentPrice = currentPrice;
        this.marketCap = marketCap;
        this.marketCapRank = marketCapRank;
        this.fullyDilutedValuation = fullyDilutedValuation;
        this.totalVolume = totalVolume;
        this.high24h = high24h;
        this.low24h = low24h;
        this.priceChange24h = priceChange24h;
        this.priceChangePercentage24h = priceChangePercentage24h;
        this.marketCapChange24h = marketCapChange24h;
        this.marketCapChangePercentage24h = marketCapChangePercentage24h;
        this.circulatingSupply = circulatingSupply;
        this.totalSupply = totalSupply;
        this.maxSupply = maxSupply;
        this.ath = ath;
        this.athChangePercentage = athChangePercentage;
        this.atl = atl;
        this.atlChangePercentage = atlChangePercentage;
        this.atlDate = atlDate;
        this.roi = roi;
        this.lastUpdated = lastUpdated;
        this.currencyType = CurrencyType.CRYPTO;


       this. fullDisplayName=id;this. shortDisplayName=name;this.code=symbol;

            }

    protected static void registerCurrency(Currency currency) {
        Objects.requireNonNull(currency, "currency must not be null");

        CURRENCIES.put(SymmetricPair.of(currency.code, currency.currencyType), currency);
    }

    protected static void registerCurrencies(Collection<Currency> currencies) {
        Objects.requireNonNull(currencies, "currencies must not be null");
        currencies.forEach(Currency::registerCurrency);
    }

    public static Currency of(String code) {
        Objects.requireNonNull(code, "code must not be null");
        if (CURRENCIES.containsKey(SymmetricPair.of(code, CurrencyType.FIAT))
                && CURRENCIES.containsKey(SymmetricPair.of(code, CurrencyType.CRYPTO))) {
            logger.error("ambiguous currency code: " + code);
            throw new IllegalArgumentException("ambiguous currency code: " + code + " (code" +
                    " is used for multiple currency types); use ofCrypto(...) or ofFiat(...) instead");
        } else {
            if (CURRENCIES.containsKey(SymmetricPair.of(code, CurrencyType.CRYPTO))) {
                return CURRENCIES.get(SymmetricPair.of(code, CurrencyType.CRYPTO));
            } else {
                return CURRENCIES.getOrDefault(SymmetricPair.of(code, CurrencyType.FIAT), NULL_CRYPTO_CURRENCY);
            }
        }
    }

    /**
     * Get the fiat currency that has a currency code equal to the
     * given {@code}. Using {@literal "¤¤¤"} as the currency code
     * returns {@literal NULL_FIAT_CURRENCY}.
     *
     * @param code
     * @return
     */
    public static FiatCurrency ofFiat(@NotNull String code) {
        if (code.equals("¤¤¤")) {
            return NULL_FIAT_CURRENCY;
        }

        FiatCurrency result = (FiatCurrency) CURRENCIES.get(SymmetricPair.of(code, CurrencyType.FIAT));
        return result == null ? NULL_FIAT_CURRENCY : result;
    }

    /**
     * Get the crypto currency that has a currency code equal to the
     * given {@code}. Using {@literal "¤¤¤"} as the currency code
     * returns {@literal NULL_CRYPTO_CURRENCY}.
     *
     * @param code
     * @return
     */
    public static CryptoCurrency ofCrypto(@NotNull String code) {
        if (code.equals("¤¤¤")) {
            return NULL_CRYPTO_CURRENCY;
        }

        CryptoCurrency result = (CryptoCurrency) CURRENCIES.get(SymmetricPair.of(code, CurrencyType.CRYPTO));
        return result == null ? NULL_CRYPTO_CURRENCY : result;
    }

    public static List<FiatCurrency> getFiatCurrencies() {
        return CURRENCIES.values().stream()
                .filter(currency -> currency.getCurrencyType() == CurrencyType.FIAT)
                .map(currency -> (FiatCurrency) currency).toList();
    }

    public static Currency lookupBySymbol(String symbol) {
        // FIXME: why fiat?
        return CURRENCIES.values().stream().filter(currency -> currency.getSymbol().equals(symbol))
                .findAny().orElse(NULL_FIAT_CURRENCY);
    }

    public static FiatCurrency lookupFiatByCode(String code) {
        return (FiatCurrency) CURRENCIES.values().stream()
                .filter(currency -> currency.currencyType == CurrencyType.FIAT && currency.code.equals(code))
                .findAny().orElse(NULL_FIAT_CURRENCY);
    }

    public static FiatCurrency lookupLocalFiatCurrency() {
        return (FiatCurrency) CURRENCIES.values().stream()
                .filter(currency -> currency.currencyType == CurrencyType.FIAT)
                .findAny().orElse(NULL_FIAT_CURRENCY);
    }

    public CurrencyType getCurrencyType() {
        return this.currencyType;
    }

    public String getFullDisplayName() {
        return this.fullDisplayName;
    }

    public String getShortDisplayName() {
        return this.shortDisplayName;
    }

    public String getCode() {
        return this.code;
    }

    public int getFractionalDigits() {
        return this.fractionalDigits;
    }

    public String getSymbol() {
        return this.symbol;
    }

    /**
     * The finality of {@code equals(...)} ensures that the equality
     * contract for subclasses must be based on currency type and code alone.
     *
     * @param object
     * @return
     */
    @Override
    public final boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (!(object instanceof Currency other)) {
            return false;
        }

        if (object == this) {
            return true;
        }

        return currencyType == other.currencyType && code.equals(other.code);
    }

    /**
     * The finality of {@code hashCode()} ensures that the equality
     * contract for subclasses must be based on currency
     * type and code alone.
     *
     * @return
     */
    @Override
    public final int hashCode() {
        return Objects.hash(currencyType, code);
    }

    private static class NullCryptoCurrency extends CryptoCurrency {}

    private static class NullFiatCurrency extends FiatCurrency {}
}
