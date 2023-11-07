package racingcar.domain.game;

import racingcar.domain.car.Car;
import racingcar.domain.car.Cars;
import racingcar.domain.game.strategy.MoveStrategy;
import racingcar.util.Splitter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RacingGame {
    private final RoundInfo roundInfo;
    private final Cars cars;

    public RacingGame(String carNames, int totalGameCount) {
        this.roundInfo = new RoundInfo(totalGameCount);
        this.cars = createCars(carNames);
    }

    public void startRacing(MoveStrategy moveStrategy) {
        this.cars.move(moveStrategy);
        this.roundInfo.increaseRound();
    }

    public boolean isEndGame() {
        return this.roundInfo.isEndGame();
    }

    public List<String> getWinners() {
        return this.cars.getWinners();
    }

    public Cars getCars() {
        return this.cars;
    }

    private Cars createCars(String inputCarName) {
        String[] carNames = Splitter.splitString(inputCarName);

        List<Car> carList = Arrays.stream(carNames)
                .map(Car::new)
                .collect(Collectors.toList());

        return new Cars(carList);
    }
}
