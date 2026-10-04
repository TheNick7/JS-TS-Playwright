AI glossary with a QA angle
#	Term	Meaning	QA angle (what to test)
1	Parameter	Learned numeric values in a model (billions+)	Not directly testable; affects capability and cost
1	Temperature	Randomness of output (0 = near-deterministic)	Run the same prompt multiple times at 0 and at high values, then compare consistency
2	Gen AI	AI that generates new text, images, code, and so on	Outputs are non-deterministic, so use tolerance-based checks, not exact match
3	Token	Chunk of text (word piece) the model reads and writes	Test limits and cost; unusual inputs such as emoji and long strings
3	RAG	Retrieval-Augmented Generation: fetch documents, then answer from them	Check the answer is grounded in the retrieved sources and that retrieval finds the right docs
3	Agentic AI	AI that plans and takes multi-step actions with tools	Verify each tool call, the order of steps, and failure recovery
3	Bias	Systematic skew in outputs	Same prompt with different names, genders, or regions; compare results
3	Embedding	Numeric vector representing meaning	Similar texts should land close together; test retrieval relevance
3	Fine-tuning	Further training on your own data	Regression-test against the base model; check for forgetting
3	Hallucination	Confident but false or unsupported output	Compare against the source of truth; test with unanswerable questions
4	Context window	Max tokens a model can consider at once	Test at, near, and over the limit; check what gets truncated
4	Vector DB	Database that stores and searches embeddings	Test recall and ranking quality, and updates and deletes
4	Schema	Defined structure for inputs or outputs (e.g., JSON)	Validate every output against the schema; test malformed cases
4	Prompt	The instruction and context sent to the model	Version prompts; test variants and edge phrasing
4	MCP	Model Context Protocol: standard way to connect models to tools and data	Test tool discovery, permissions, and error handling
5	LangChain	Framework for building LLM applications	Test chain steps individually and end to end
5	Skills	Reusable instruction packages that guide the model on specific tasks	Check they trigger on the right requests and not on wrong ones
5	Neural network	Layered model that learns patterns from data	Black box, so test behavior, not internals
5	Agents	LLM + tools + loop, acting toward a goal	Test goal completion, loops, and runaway cost
6	Weights	The learned values stored in the model	Open-weight means you can download them; pin the version you tested
6	Chatbot	Conversational interface to a model	Multi-turn memory, tone, escalation to a human
6	Attention	Mechanism that lets the model weigh which tokens matter (multiple "heads")	Long-context tests: does it still use information from the middle?
6	Harness	The scaffolding (prompts, tools, loop) around a model	Same model can score very differently in different harnesses, so fix it when comparing
7	Prompt injection	Malicious instructions hidden in input or retrieved content	Red-team: "ignore previous instructions", poisoned documents
7	Guardrails	Rules and filters limiting inputs and outputs	Test that they block bad content without blocking valid use
7	Parsing	Converting model output into structured data	Test malformed JSON, extra text, missing fields
7	Evals	Systematic tests that score model quality	Automated suites with a golden dataset, run on every change
7	Chunks	Pieces documents are split into for retrieval	Test chunk size and overlap; check answers aren't split across chunks
8	Ground truth	Verified correct answers used as the reference