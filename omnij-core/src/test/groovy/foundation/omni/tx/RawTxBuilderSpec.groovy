package foundation.omni.tx

import org.bitcoinj.core.Coin
import spock.lang.Shared
import spock.lang.Specification

import static foundation.omni.CurrencyID.MSC


/**
 * RawTxBuilder unit tests
 */
class RawTxBuilderSpec extends Specification {
    @Shared
    RawTxBuilder builder

    def setup() {
        builder = new RawTxBuilder()
    }

    def "The generated hex-encoded Simple Send transaction matches a valid reference transaction"() {
        when:
        def txHex = builder.createSimpleSendHex(MSC,
                Coin.COIN_VALUE)

        then:
        txHex == "00000000000000010000000005f5e100"
    }

    def "The generated hex-encoded Simple To Owners transaction matches a valid reference transaction"() {
        when:
        def txHex = builder.createSendToOwnersHex(MSC,
                Coin.COIN_VALUE)

        then:
        txHex == "00000003000000010000000005f5e100"
    }

    def "The generated hex-encoded Dex Sell Offer transaction matches a valid reference transaction"() {
        when:
        def txHex = builder.createDexSellOfferHex(MSC,
                Coin.COIN_VALUE,    // 1 BTC in Satoshis
                20000000,           // 0.2 BTC in Satoshis
                (Byte) 10,
                10000,              // Fee in Satoshis
                (Byte) 1)

        then:
        txHex == "00010014000000010000000005f5e1000000000001312d000a000000000000271001"
    }
}