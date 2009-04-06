/*
* generated by Xtext
*/
package org.eclipse.emf.compare.epatch.dsl.parser.packrat.consumers;

import org.eclipse.emf.ecore.EClassifier;

import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.RuleCall;

import org.eclipse.xtext.parser.packrat.consumers.IElementConsumer;
import org.eclipse.xtext.parser.packrat.consumers.INonTerminalConsumer;
import org.eclipse.xtext.parser.packrat.consumers.INonTerminalConsumerConfiguration;
import org.eclipse.xtext.parser.packrat.consumers.ITerminalConsumer;
import org.eclipse.xtext.parser.packrat.consumers.NonTerminalConsumer;

import org.eclipse.emf.compare.epatch.dsl.services.EpatchGrammarAccess.CreatedObjectElements;

public final class EpatchCreatedObjectConsumer extends NonTerminalConsumer {

	private CreatedObjectElements rule;	

	private INonTerminalConsumer objectCopyConsumer;

	private INonTerminalConsumer objectNewConsumer;

	private IElementConsumer alternatives$1$Consumer;

	private IElementConsumer ruleCall$2$Consumer;

	private IElementConsumer ruleCall$3$Consumer;

	protected class Alternatives$1$Consumer extends AlternativesConsumer {
		
		protected Alternatives$1$Consumer(final Alternatives alternatives) {
			super(alternatives);
		}
		
		@Override
		protected void doGetConsumers(ConsumerAcceptor acceptor) {
			acceptor.accept(ruleCall$2$Consumer);
			acceptor.accept(ruleCall$3$Consumer);
		}
	}

	protected class RuleCall$2$Consumer extends ElementConsumer<RuleCall> {
		
		protected RuleCall$2$Consumer(final RuleCall ruleCall) {
			super(ruleCall);
		}
		
		@Override
		protected int doConsume(boolean optional) throws Exception {
			return consumeNonTerminal(objectNewConsumer, null, false, false, false, getElement(), optional);
		}
	}

	protected class RuleCall$3$Consumer extends ElementConsumer<RuleCall> {
		
		protected RuleCall$3$Consumer(final RuleCall ruleCall) {
			super(ruleCall);
		}
		
		@Override
		protected int doConsume(boolean optional) throws Exception {
			return consumeNonTerminal(objectCopyConsumer, null, false, false, false, getElement(), optional);
		}
	}

	public EpatchCreatedObjectConsumer(INonTerminalConsumerConfiguration configuration, ITerminalConsumer[] hiddenTokens) {
		super(configuration, hiddenTokens);
	}
	
	@Override
	protected int doConsume() throws Exception {
		return alternatives$1$Consumer.consume();
	}

	public CreatedObjectElements getRule() {
		return rule;
	}
	
	public void setRule(CreatedObjectElements rule) {
		this.rule = rule;
		
		alternatives$1$Consumer = new Alternatives$1$Consumer(rule.getAlternatives());
		ruleCall$2$Consumer = new RuleCall$2$Consumer(rule.getObjectNewParserRuleCall_0());
		ruleCall$3$Consumer = new RuleCall$3$Consumer(rule.getObjectCopyParserRuleCall_1());
	}
	
	@Override
	protected AbstractRule getGrammarElement() {
		return getRule().getRule();
	}

	@Override
	protected EClassifier getDefaultType() {
		return getGrammarElement().getType().getClassifier();
	}
	
	public void setObjectCopyConsumer(INonTerminalConsumer objectCopyConsumer) {
		this.objectCopyConsumer = objectCopyConsumer;
	}
	
	public void setObjectNewConsumer(INonTerminalConsumer objectNewConsumer) {
		this.objectNewConsumer = objectNewConsumer;
	}
	
}
