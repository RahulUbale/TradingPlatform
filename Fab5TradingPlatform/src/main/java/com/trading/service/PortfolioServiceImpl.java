package com.trading.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.trading.api.KiteConnectAPI;
import com.trading.bean.OrderQuote;
import com.trading.bean.OverallReturn;
import com.trading.bean.Portfolio;
import com.trading.bean.PortfolioCost;
import com.trading.bean.PortfolioValue;
import com.trading.bean.Return;
import com.trading.pojos.Position;
import com.trading.pojos.User;

@Service
@Transactional
public class PortfolioServiceImpl implements IPortfolioService {

	@Override
	public Portfolio getPortfolio(User user) {

		List<Position> positions = user.getPositions();
		double portfolioCost = 0;
		double portfolioValue = 0;
		double overallReturn = 0;
		double overallReturnInpercent = 0;

		Set<String> investedCompanies = new HashSet<>();

		List<Return> returns = new ArrayList<>();

		for (Position p : positions) {
			investedCompanies.add(p.getApiUniqueName());
		}

		for (String apiUniqueName : investedCompanies) {
			Return newReturn = new Return();
			double totalCost = 0;
			double avgCost = 0;
			double gain = 0;
			double gainInPercent = 0;
			int shares = 0;
			for (Position position : positions) {
				if (apiUniqueName.equals(position.getApiUniqueName())) {
					newReturn.setSymbol(apiUniqueName);
					newReturn.setCompany(position.getCompany());
					totalCost = totalCost + (position.getPrice() * position.getQuantity());
					shares = shares + position.getQuantity();
				}
			}

			avgCost = totalCost / shares;
			System.out.println("apiUnique Name is " + apiUniqueName);
			OrderQuote quote = KiteConnectAPI.getQuote(apiUniqueName);
			System.out.println("Quote is : " + quote.getLastPrice());
			System.out.println(quote);
			double close = quote.getOhlc().getClose();
			double price = quote.getLastPrice();
			double change = quote.getChange();
			double changeInPercent = (change / close) * 100;
			double marketValue = price * shares;
			gain = marketValue - totalCost;
			gainInPercent = (gain / totalCost) * 100;

			newReturn.setAverageCost(avgCost);
			newReturn.setChange(change);
			newReturn.setChangeInPercent(changeInPercent);
			newReturn.setTotalCost(totalCost);
			newReturn.setShares(shares);
			newReturn.setMarketValue(marketValue);
			newReturn.setPrice(price);
			newReturn.setGain(gain);
			newReturn.setGainInPercent(gainInPercent);
			returns.add(newReturn);

			portfolioValue = portfolioValue + marketValue;
			portfolioCost = portfolioCost + totalCost;

		}

		overallReturn = portfolioValue - portfolioCost;
		overallReturnInpercent = (overallReturn / portfolioCost) * 100;
		OverallReturn overAllReturn = new OverallReturn(overallReturn, overallReturnInpercent);
		PortfolioCost portCost = new PortfolioCost(portfolioCost);
		PortfolioValue portValue = new PortfolioValue(portfolioValue);
		Portfolio portfolio = new Portfolio(overAllReturn, portCost, portValue, returns);

		return portfolio;
	}

}
