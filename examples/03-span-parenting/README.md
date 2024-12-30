# Span Parenting
---

Span parenting is an important aspect of tracing. Each span has an optional parent.
The span APIs are built on top of context, which allows span context to be implicitly passed around an application and across threads. When a span is created, its parent is set to the whatever span is present in Context.current() unless there is no span or the context is explicitly overridden.


We'll present in this project 2 ways to create span parenting: By explicitly setting the parent on a span, and by creating a span inside another active span.
