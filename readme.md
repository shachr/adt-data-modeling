<h1>Definition of Event-Driven APIs</h1>


# Working Group

Approver: [Tristan Baker](mailto:tristan_baker@intuit.com)

Driver: [Shachar Bar David](mailto:shachar_bardavid@intuit.com)

Core Contributors: [Amit Arya](mailto:amit_arya@intuit.com) [Greg Kesler](mailto:greg_kesler@intuit.com) [Vidya Narayanan](mailto:vidya_narayanan@intuit.com)

Contributors: [Jason Webb](mailto:jason_webb@intuit.com) [Alex Collins](mailto:alex_collins@intuit.com) [O. Felix Amoruwa](mailto:ofelix_amoruwa@intuit.com) [Dhruv Chandna](mailto:dhruv_chandna@intuit.com)

Status: RFA


# Context

As Intuit shifts toward distributed, loosely coupled architecture style, every system must appropriately and efficiently communicate with other systems.

In response to these demands, Intuit adopted event-driven architectures powered by modern message brokers such as Apache Kafka and Apache Pulsar to communicate between systems in an asynchronous fashion via event-driven APIs.

The new event-driven approach introduces new challenges, such as discoverability, documentation, schema registration, and access to event-driven APIs. The lack of standardization requires the help of the API owner and limits the potential for libraries, tooling, and infrastructure to aid with the delivery and interoperability of event-driven APIs.


# Scope

Following the [Intuit API Stewardship](https://github.intuit.com/intuit-tech-arch-decisions/intuit-wide-decisions/blob/master/doc/adr/0080-intuit-api-stewardship.md) decision to define a standard for describing APIs in a common way via an Intuit wide common working group and reviews process, this decision will establish an Intuit-wide technology agnostic standard for describing event-driven APIs in a common way. This includes events described in Event Bus, OMS, ICE (Websockets) etc.


# Out of Scope

The decision for tooling required to aid the developers to author and discover the Async APIs is out of scope of this document.


# Current State

In the current state, the City Map consists of two independent capabilities for building event-driven APIs. The L2 Development / Platform Runtime / Service Communications and the L2 Data / Data Persistence / Event Bus. The current process for building event-driven APIs has variability in standards with disconnected strategies and they are often optimized locally. We neither have a clearly documented standard to build event-driven APIs across Intuit nor a mechanism to enforce the standard.

The L2 Data / Data Persistence / Event Bus capability supports a topic discovery and schema registration using the L2 Data / Data Understanding / Data Map Registry which was previously known as Metadata Registry, hereafter MDR. The MDR schema registry is specific to the EventBus capability, does not integrate well with open source tools, such as code generation, documentation, etc., and is not compatible with modern message brokers such as Apache Kafka and Apache Pulsar out of the box.

The L2 Development / Platform Runtime / Service Communications does not support channel discovery.

There is a need for an intuit-wide technology agnostic standard for describing event-driven APIs that enable both humans and machines to discover, understand, and easily integrate.


# Decision

Intuit MUST adopt [AsyncAPI](https://www.asyncapi.com/) as a standard for declaring and exposing event-driven APIs.


# Rationale

[AsyncAPI](https://www.asyncapi.com/) is a unified, open source, protocol-agnostic asynchronous specification,  inspired by the [OpenAPI](https://www.openapis.org/) specification, that is both human-readable and machine-readable while also being backed by a diverse and rich tooling ecosystem. With its maturity and elegant abstractions, the [AsyncAPI](https://www.asyncapi.com/) Specification has emerged as the industry standard for defining asynchronous event-driven APIs and is being adopted by several technology companies such as Slack, PayPal, Salesforce, SAP, and IBM, among others.The following diagram depicts the structure of the AsyncAPI specification document:
<br/><br/>
[IMAGE HERE](https://www.asyncapi.com/docs/tutorials/getting-started/coming-from-openapi)
<br/><small>AsyncAPI specification describing channels, operations,
messages their description and tags.</small>


The “Message” defined in the diagram above must conform to the [Intuit Event Header Specification](https://github.intuit.com/pages/intuit-one-api/one-api-standards-governance/#/async/event-headers-overview).

As of writing this decision, CloudEvents is providing a specification for [channel discovery](https://github.com/cloudevents/spec/blob/main/discovery/spec.md) in addition to the message header specification. However, it is still a work in progress, and there are no known open-source implementations.


# Implications

- Any new and existing event-driven API must be declared via [asyncAPI](https://www.asyncapi.com/).
- Necessary design time and runtime tools need to be developed and a strategy to deprecate existing tools needs to be defined


# Links

- [Intuit API Stewardship decision](https://github.intuit.com/intuit-tech-arch-decisions/intuit-wide-decisions/blob/master/doc/adr/0080-intuit-api-stewardship.md)
- [AsyncAPI Specification](https://www.asyncapi.com/docs/tutorials/getting-started/coming-from-openapi)
