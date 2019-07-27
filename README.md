# Doc_Pipeline

This is a prototype pipeline that focuses on generation of documentation from your Git repository. This has been developed as part of the SW Factory series for the DevOps for Defense meetup.

This is published as open source using a MIT license.  If you find it interesting or useful, hopefully you can adopt and tailor this to meet your needs.

This project is exploring one solution to the challenge of managing documentation.  Historically, we often have mountains of documentation that have to be created and delivered to our customers. Often this documentation is as valuable (or sometimes more valuable) to our customers than our working code. Unfortunately, the creation and management of this documentation is often a purely manual process.  The tool of choice tends to be the MS Office suite which does not inherently support good configuration management.  As a result, we often have a shared folder with our my_doc_v1.docx, my_doc_final.docx, my_doc_final_final.docx, and my_doc_final_final_seriously_this_time.docx content.  Collaborating with members of your team is difficult to do.  And merging different versions without corrupting everything is almost impossible.

The solution we put forward here is to build a document using Jekyll.  Jekyll is a tool developed to generate static web sites from markdown files.  We have adapted that to generate a deliverable quality document using markdown files for each document section.  The templates provided here are an example of how you can format a web site to optimize for print (e.g. print-to-PDF) over web browser usage.  While this does attempt to generate high quality deliverable documentation, the automation used doesn't produce a perfect document as may be produced by a professional technical editor.  That said, how often do we really have access to a professional technical editor?

## Prerequisites

Here's the list of tools I'm using for the prototype:
* Ubuntu MATE 18.04 - OS Environment
* Python 3 - Tool Scripting
* Jekyll - HTML generation
* Hunspell - Spell checking
* Jenkins - Orchestration
* Pandoc - Markdown to HTML (for spell checking only)