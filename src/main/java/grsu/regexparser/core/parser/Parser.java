package grsu.regexparser.core.parser;

import grsu.regexparser.core.machine.MachineGenerator;
import grsu.regexparser.core.state.State;
import grsu.regexparser.core.wrapper.StrIteratorWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Parser {

    private MachineGenerator machineGenerator = new MachineGenerator();

    public List<String> getSubStringsByRegex(String content, String regex){
        final State rootState = machineGenerator.generateStateMachineForRegexStrAndGetRootState(regex);
        AtomicReference<State> currentState = new AtomicReference<>(rootState);
        StringBuilder subString=new StringBuilder();
        StrIteratorWrapper strIteratorWrapper=new StrIteratorWrapper(content.toCharArray());
        List<String> resultSubStrings=new ArrayList<>();

        while(strIteratorWrapper.indexLessThanRegexStrLength()){
                        currentState.get()
                                .getOutcomesPaths()
                                .stream()
                                .filter(path -> path.canUseThisPath(strIteratorWrapper))
                                .findFirst()
                                .ifPresentOrElse(
                                        path -> {
                                            currentState.set(path.getToState());
                                            subString.append(strIteratorWrapper.getCurrentSymbol());
                                            if(strIteratorWrapper.currentCharIsLast()){
                                                resultSubStrings.add(subString.toString());
                                            }
                                            strIteratorWrapper.incrementIndex();
                                        },
                                        ()->{
                                            if(currentState.get().isFinalState()){
                                                resultSubStrings.add(subString.toString());
                                            }
                                            if(currentState.get() == rootState){
                                                strIteratorWrapper.incrementIndex();
                                            }
                                            currentState.set(rootState);
                                            subString.delete(0,subString.length());
                                        }
                                );

        }
        return resultSubStrings;
    }

}
